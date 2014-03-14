package rscore.helper

import scala.collection.mutable.Stack
import org.eclipse.ltk.core.refactoring.Change

object UndoManager {
	
	val undoStack = new Stack[Change]
	
	def pushUndo(chg: Change): Unit = {
		undoStack.push(chg)
	}
	
	def popUndo(): Change = {
		return undoStack.pop
	}

}