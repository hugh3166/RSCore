package dsl.traits.search 
import scala.util.matching.Regex

trait NameBasedSearchable {
	// �G���e�B�e�B���̎擾���@�����ꂼ��ňꏏ�Ȃ�C�����ŋ�̓I�Ȏ擾���@�������āC�����N���X�ւ́i�ł́j�Ϗ�������
	val name: String
	
	// ���O�̊��S��v
	private def hasName(name: String): Boolean = {
		return this.name == name
	}
	
	// ���K�\���ň�v
	// TODO: �����Ƃ����}�b�`�������@������͂�
	private def hasRegexMatchedName(reg: Regex): Boolean = {
		reg findPrefixOf this.name match{
			case Some(_) => return true
			case None => return false
		}
	}
	
	def hasNamesOr(names: Array[String]): Boolean = {
		return names.exists(hasName(_))
	}
	
	// nameRagexes ���̐��K�\����1�Ƀ}�b�`�����OK
	// nameRagexes �̗v�f�́CRegex �I�u�W�F�N�g�ł��C�ϊ�����O�̕�����ł��悢
	def hasRegexeMathcedNamesOr(nameRegexes: Array[Regex]): Boolean ={
		return nameRegexes.exists(hasRegexMatchedName(_))
	}
	def hasRegexeMathcedNamesOr(nameRegexStrings: Array[String]): Boolean ={
		return nameRegexStrings.exists(e => hasRegexMatchedName(e.r))
	}
}