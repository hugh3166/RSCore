package rscore.tests.interpreter.dsl.collection;

import org.junit.Test
import org.jruby.embed.ScriptingContainer
import rscore.dsl.util.ImplicitConversions._
import org.junit.Ignore

class EvalTest {
	@Ignore
	def �C���^�[�v���^���Ăяo����(): Unit = {
		val container: ScriptingContainer = new ScriptingContainer();
		val source = """
			require 'java'
			java_import 'dsl.entity.RSWorkspace'
			java_import 'dsl.entity.collection.By'
			java_import 'dsl.entity.collection.With'
			java_import 'dsl.entity.collection.WithOr'
			java_import 'dsl.entity.collection.WithAnd'
			
			w = WithOr.new(["public"])
			q = By.Modifier(w)
			
			#### WORKS! ####
			# RSWorkspace.project("Sample").pkg("test.dsl").classes.first.constructors.size
			# RSWorkspace.project("Sample").pkg("test.dsl").classes.first.constructors.first.introduce_factory
			
			# RSWorkspace.project("Sample").pkg("test.dsl").classes.first.constructors.first.introduce_factory
			RSWorkspace.project("Sample").pkg("test.dsl").classes.first.introduce_factory
			
			# RSWorkspace.project("Sample").pkg("test.dsl").classes.first.fields.first.rename("newnewnew")
			# RSWorkspace.project("Sample").pkg("test.dsl").classes.first.fields.first.encapsulate()
			
			#### EXPERIMENTAL ###
			# puts RSWorkspace.project("Sample").pkg("test.dsl").classes.select_with_foo
			# puts RSWorkspace.project("Sample").pkg("test.dsl").classes.first.fields.select_with_foo
			
			
			# f.rename("")
		"""

		val res = container.runScriptlet(source);

		println(res.toString());
	}

}