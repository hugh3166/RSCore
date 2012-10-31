package tests.dsl
import dsl.entity.RSWorkspace
import dsl.entity.RSProject
import org.junit.Before
import org.junit.Test
import org.junit.Assert._

/**
 * ���ׂẴe�X�g�̊��e�X�g
 * 
 * NOTICE: 
 * ���[�N�X�y�[�X�Ɛݒ�t�@�C���́C�ʏ�N���Ɠ������̂��g�p����̂ŁC�N���A���Ȃ��I
 */
class BaseTest {
	val $ = RSWorkspace
	var project: RSProject = null  
	
	@Before
	def setUp(): Unit = {
		$.refresh()
		println("Workspace has been refreshed")
		
		this.project = $.project("Sample")
		println("Project has been set")
	}
	
}