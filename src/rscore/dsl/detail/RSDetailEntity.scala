package rscore.dsl.detail
import org.eclipse.jdt.core.dom.ASTNode
import rscore.dsl.common.RSObject
import rscore.dsl.common.RSNullObject
import rscore.dsl.traits.action.RSTExtractMethod

class RSDetailEntity(node: ASTNode, val parent: RSObject = RSNullObject) 
	extends RSObject with RSTExtractMethod {
	// TODO: <y> merge continuous statements to one Entity
	
	val self = this
	
	def startPos(): Int = node.getStartPosition()
	def length(): Int = node.getLength()
	def origin: ASTNode = node
}