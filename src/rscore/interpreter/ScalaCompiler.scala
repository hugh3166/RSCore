package rscore.interpreter

import scala.io.Source
import scala.util.Random
import scala.tools.nsc.{Global, Settings}
import scala.tools.nsc.reporters.ConsoleReporter
import scala.tools.nsc.util.BatchSourceFile
import scala.tools.nsc.interpreter.AbstractFileClassLoader
import java.io.{PrintWriter, FileOutputStream}
import java.io.File

object ScalaCompiler {
	private val SCRIPTENCODE: String = "UTF-8"
	private val COMPILECLASSNAME: String = "MyScriptClass"

	private val mySettings: Settings = new Settings
	mySettings.deprecation.value = true  // deprecative warning
	mySettings.unchecked.value = true  // unchecked warning
	mySettings.outputDirs.setSingleOutput(ScalaInterpreterA.rootPath)	// set output directory to (RSCore/bin)
	mySettings.classpath.append(ScalaInterpreterA.rootPath)	// set classpath to (RSCore/bin)
	mySettings.bootclasspath.value = ScalaInterpreterA.bootClassPath.mkString(File.pathSeparator)

	// TODO: Clear. For testing, write reports to desktop
	private val out = new PrintWriter(new FileOutputStream("/Users/young/Desktop/Compiler_output.txt"))
	private val global: Global = new Global(mySettings, new ConsoleReporter(mySettings, Console.in, out))  // Reporter output to console

	private val classLoader = getClass.getClassLoader()
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
  	
}
