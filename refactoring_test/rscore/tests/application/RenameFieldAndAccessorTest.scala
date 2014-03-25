package rscore.tests.application
import org.junit.Test
import rscore.dsl.entity.collection.By

class RenameFieldAndAccessorTest extends ApplicationTest{
	@Test
	def �t�B�[���h���Ή�����A�N�Z�T�̖��O�Q�b�^�̃p�����[�^����ύX����(): Unit = {
		val testName = "RenameFieldAndAccessorTest"
		prepareTest(testName)

		val f = getFirstClass().fields().first
		
		// capitalize �� Ruby �ɂ����� (String#capitalize(!))
		val setterName = "set" + f.name.capitalize
		val getterName = "get" + f.name.capitalize
		val getter = getFirstClass.methods.selectOne(By name getterName)
		val setter = getFirstClass.methods.selectOne(By name setterName)
		val parameter = setter.parameters().selectOne(By name f.name)
		
		val newName = "b"
		f.rename(newName)
		parameter.rename(newName)
		
		val newSetterName = "set" + newName.capitalize
		setter.rename(newSetterName)
		
		val newGetterName = "get" + newName.capitalize
		getter.rename(newGetterName)
		
		doAssert(testName)
	}


}