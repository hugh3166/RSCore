package tests.dsl
import dsl.entity.RSWorkspace
import dsl.entity.RSProject

/**
 * ���ׂẴe�X�g�̊��e�X�g
 * 
 * NOTICE: 
 * ���[�N�X�y�[�X�Ɛݒ�t�@�C���́C�ʏ�N���Ɠ������̂��g�p����̂ŁC�N���A���Ȃ��I
 */
class BaseTest {
	val $ = RSWorkspace
	var project: RSProject = null  
}