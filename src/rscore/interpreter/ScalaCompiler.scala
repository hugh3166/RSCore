package rscore.interpreter

import scala.io.Source
import scala.util.Random
import scala.tools.nsc.{Global, Settings}
import scala.tools.nsc.reporters.ConsoleReporter
import scala.tools.nsc.util.BatchSourceFile
import scala.tools.nsc.interpreter.AbstractFileClassLoader
import java.io.{PrintWriter, FileOutputStream}
import java.io.File
import java.io.FilenameFilter

object ScalaCompiler {
	
	class JarFilter extends FilenameFilter {
		def accept(dir: File, name: String):Boolean = {
			name.endsWith(".jar")
		}
	}
  
	private val SCRIPTENCODE: String = "UTF-8"
	private val COMPILECLASSNAME: String = "MyScriptClass"
	
	val rootPath = getClass.getProtectionDomain().getCodeSource().getLocation().getPath() + "bin"
	val selfPath = getResourcePath("/rscore/dsl/common/RSObject.class")
	val scalaLibraryPath = getResourcePath("/scala/ScalaObject.class")
	val scalaCompilerPath = getResourcePath("/scala/tools/nsc/Global.class")
	val eclipseCorePath = getResourcePath("/org/eclipse/core/resources/ResourcesPlugin.class")
	val eclipseJdtPath = getResourcePath("/org/eclipse/jdt/core/JavaCore.class")
	val runtimePath = getResourcePath("/org/eclipse/core/runtime/NullProgressMonitor.class")
	val runtimePath2 = getResourcePath("/org/eclipse/core/runtime/jobs/ISchedulingRule.class")
	val runtimePath3 = getResourcePath("/org/eclipse/core/runtime/content/IContentTypeMatcher.class")
	val rubyPath = getResourcePath("/org/jruby/Ruby.class")
//	val bootClassPath = List(scalaLibraryPath, scalaCompilerPath, eclipseCorePath, eclipseJdtPath, runtimePath, runtimePath2, runtimePath3, rubyPath, rootPath)

	val dir = new File("/Applications/eclipse_kepler/plugins")
	val fs = dir.listFiles(new JarFilter)
	
	var bootClassPath = List.empty[String]
	fs.foreach(f => {
	    bootClassPath = f.toString::bootClassPath
	} )
	bootClassPath = scalaLibraryPath::scalaCompilerPath::rootPath::rubyPath::bootClassPath
	
	private val mySettings: Settings = new Settings
	mySettings.deprecation.value = true  // deprecative warning
	mySettings.unchecked.value = true  // unchecked warning
	mySettings.outputDirs.setSingleOutput(rootPath)	// set output directory to (RSCore/bin)
	mySettings.classpath.append(rootPath)	// set classpath to (RSCore/bin)
	mySettings.bootclasspath.value = bootClassPath.mkString(File.pathSeparator)

	// TODO: Clear. For testing, write reports to desktop
	private val out = new PrintWriter(new FileOutputStream("/Users/young/Desktop/Compiler_output.txt"))
	private val global: Global = new Global(mySettings, new ConsoleReporter(mySettings, Console.in, out))  // Reporter output to console

	private val classLoader = getClass.getClassLoader
  	// use self's ClassLoader to load compiled class

	// compile from file using class name gave by parameter
	def compileFromFile(scriptPath: String, className: String): Option[Class[_]] = {
		val source = Source.fromFile(scriptPath, SCRIPTENCODE)
	    try {
	    	compileClass(className, source.mkString, scriptPath)
	    } finally {
	    	if (source != null) source.close()
	    }
	}

	// compile a class and load it
	def compileClass(className: String, source: String, sourcePath: String = "[dynamic compiler]"): Option[Class[_]] = try {
		val compiler = new global.Run
	    compiler.compileSources(List(new BatchSourceFile(sourcePath, source)))
	    
	    // load class to JavaVM
	    Some(classLoader.loadClass(className))
	} catch {
	    case e: Throwable =>
	      	e.printStackTrace(out)
	      	val strErr = e.toString
	      	println(strErr)
	      	None
	}

	// run a script from file
	def runScriptFromFile(scriptPath: String): Unit = {
	    val source = Source.fromFile(scriptPath, SCRIPTENCODE)
	    try {
	    	runScript(source.mkString)
	    } finally {
	      	if (source != null) source.close()
	    }
	}

	// run script (by compiling it)
	def runScript(source: String): Unit = try {
	    val scriptClassName = wrapScriptClassName
	    val wrappedSource = wrapScript(source, scriptClassName)
	
	    // for compiled class, generate its instance and invoke its non-parameter function as Scala.Function0
	    compileClass(scriptClassName, wrappedSource) foreach { cls =>
	      	cls.newInstance.asInstanceOf[() => Any].apply()
	    }
	} catch {
	    case e: Throwable => e.printStackTrace(out)
	} finally {
	    // TODO: File clear
	}
	
	// wrap script with necessary code (import file and..)
  	private def wrapScript(code: String, className: String): String = {
  		// TODO: <y> wrap with try to handle runtime error
  		""" |import rscore.dsl.entity._
			|import rscore.dsl.entity.collection._
			|class %s extends (() => Any) {
  			|	def apply() = {
  			|		%s
  			|  }
  			|}
  			|""".stripMargin.format(className, code)
  	}
  	
  	private def wrapScriptClassName: String = {
  		val random = new Random
  		return COMPILECLASSNAME + random.nextInt(Integer.MAX_VALUE)
  	}
  	
  	// get resource path from current runtime
	private def getResourcePath(resourceName: String): String = {
		val resourceURL = getClass.getClassLoader.getResource(resourceName)
		val nativeURL = org.eclipse.core.runtime.Platform.resolve(resourceURL)
		val path = nativeURL.getPath()
		
		val indexOfFileScheme = if(path.indexOf("file:") >= 0) path.indexOf("file:") + 5 else 0 
		val indexOfSeparator = path.lastIndexOf('!')

		if (indexOfSeparator >= 0)
			path.substring(indexOfFileScheme, indexOfSeparator)
		else
			path.substring(indexOfFileScheme)
	}
  	
}
