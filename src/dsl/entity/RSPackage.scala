package dsl.entity
import org.eclipse.jdt.core.IPackageFragment
import dsl.entity.collection.RSClasses._
import dsl.entity.collection.RSClasses
import dsl.entity.RSClass

class RSPackage(pkg: IPackageFragment) {
	/**
	 * �p�b�P�[�W�ȉ��Ő錾����Ă���N���X(IType)�����ׂĎ擾����
	 * @param includeNested �l�X�g���ꂽ�N���X�i�C���^�[�i���N���X�j���擾���邩�ǂ���
	 */
	def classes(includeNested: Boolean = false): Array[RSClass] = {
		var result = List[RSClass]()
		var cus = pkg.getCompilationUnits()
		if (includeNested) {
			for (cu <- cus) { result = result ::: cu.getAllTypes().map(e => new RSClass(e)).toList }
		} else {
			for (cu <- cus) { result = result ::: cu.getTypes().map(e => new RSClass(e)).toList }
		}
		return result.toArray[RSClass]
	}
	def classes(): Array[RSClass] = classes(true)

}