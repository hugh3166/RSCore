package rscore.interpreter

import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter._
import java.io.{PrintWriter, FileOutputStream}
import java.io.File

object ScalaInterpreterA {
	var myMain: IMain = _
	val mySettings: Settings = new Settings
	val out: PrintWriter = new PrintWriter(new FileOutputStream("/Users/young/Desktop/Interpreter_output.txt"))

	// bootClassPath
	/*	import org.eclipse.core.resources.ResourcesPlugin
		import org.eclipse.jdt.core.JavaCore
		import org.eclipse.core.runtime.NullProgressMonitor
	 * */
	val rootPath = getResourcePath("/")
	//val selfPath = getResourcePath("/rscore/dsl/common/RSObject.class")
	val scalaLibraryPath = getResourcePath("/scala/ScalaObject.class")
	val scalaCompilerPath = getResourcePath("/scala/tools/nsc/Global.class")
	val eclipseCorePath = getResourcePath("/org/eclipse/core/resources/ResourcesPlugin.class")
	val eclipseJdtPath = getResourcePath("/org/eclipse/jdt/core/JavaCore.class")
	val runtimePath = getResourcePath("/org/eclipse/core/runtime/NullProgressMonitor.class")
	val runtimePath2 = getResourcePath("/org/eclipse/core/runtime/jobs/ISchedulingRule.class")
	val rubyPath = getResourcePath("/org/jruby/Ruby.class")
	val bootClassPath = List(scalaLibraryPath, scalaCompilerPath, eclipseCorePath, eclipseJdtPath, runtimePath, runtimePath2, rubyPath)

	mySettings.deprecation.value = true
	mySettings.unchecked.value = true
	mySettings.bootclasspath.value = bootClassPath.mkString(File.pathSeparator)	// configure bootclasspath 
	mySettings.classpath.append(rootPath)	// set classpath to (RSCore/bin)
	
	// TODO: Use Scala's Interpreter.
	def initScalaInterpreter(): Unit = {
		myMain = new IMain(mySettings, out)
		myMain.initializeSynchronous()
		assert(myMain.isInitializeComplete)
	}
	
	def execScalaScript(script: String): Unit = {
		val res = myMain.interpret(script)
		out.println(res)
	}
	
	// close reporter's file
	def finalizeWriter() = {
	    out.close()
	}
	
	// get resource path from current runtime
	private def getResourcePath(resourceName: String): String = {
		val resourceURL = getClass.getResource(resourceName)
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