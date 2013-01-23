package rscore.tests.refactoring
import org.eclipse.jdt.core.ICompilationUnit
import org.eclipse.jdt.core.IPackageFragment
import rscore.util.FileUtil
import org.junit.Before
import org.junit.Assert
import org.junit.Test
import org.eclipse.core.runtime.NullProgressMonitor
import org.junit.After
import rscore.dsl.entity.RSWorkspace
import rscore.dsl.util.ImplicitConversions._
import rscore.dsl.entity.collection.By
import rscore.dsl.entity.collection.With
import org.junit.Ignore
import java.io.FileNotFoundException

class IntroduceFactoryTest extends RefactoringBaseTest {
	override val testGroupIdentifier = "introduce_factory"

	@Before
	override def setUp(): Unit = {
		super.setUp()
	}

	@Test
	def �N���X��`�̂�(): Unit = {
		val testName = "DeclarationOnly"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first.introduce_factory

		doAssert(testName)
	}

	@Test
	def �N���X��`�̂�2(): Unit = {
		val testName = "DeclarationOnly2"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first.introduce_factory

		doAssert(testName)
	}

	@Test
	def �N���X��`�ƃR���X�g���N�^�Ăяo��() = {
		val testName = "DeclarationAndCaller"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first.introduce_factory

		doAssert(testName)
	}

	@Test
	def �R���X�g���N�^���w�肵�ăN���X��`�݂̂����t�@�N�^�����O(): Unit = {
		val testName = "DeclarationOnly"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first.constructors.first.introduce_factory

		doAssert(testName)

	}

	@Test
	def �R���X�g���N�^���w�肵�ăN���X��`�ƃR���X�g���N�^�Ăяo�������t�@�N�^�����O(): Unit = {
		val testName = "DeclarationAndCaller"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first.constructors.first.introduce_factory

		doAssert(testName)
	}

	@Test
	def �����̃R���X�g���N�^������ꍇ�ɃN���X���w�肵�Ă��ׂẴR���X�g���N�^�����t�@�N�^�����O(): Unit = {
		val testName = "MultipleConstructor"
		prepareTest(testName)

		val $ = RSWorkspace
		$.project(projectName).pkg(testGroupIdentifier).classes.select(By.Name(testName)).first.introduce_factory

		doAssert(testName)
	}

}