package rscore.helper

import scala.collection.mutable.Stack
import org.eclipse.ltk.core.refactoring.Change
import org.eclipse.core.runtime.NullProgressMonitor

object UndoManager {
	
	val undoStack: Stack[Stack[Change]] = new Stack[Stack[Change]]
	var tempUndoStack: Stack[Change] = _
	
	def prepareUndo(): Unit = {
	  tempUndoStack = new Stack[Change]
	}
	
	def finalizeUndo(): Unit = {
	  undoStack.push(tempUndoStack)
	}
	
	def pushUndo(chg: Change): Unit = {
	  if (tempUndoStack != null){
	    tempUndoStack.push(chg)
	  }
	  // TODO: throw error
	}
	
	def popUndo(): Change = {
	  if (tempUndoStack != null){
	    return tempUndoStack.pop
	  }
	  // TODO: throw error
	  return null
	}
	
	def undo(): Unit = {
	  if (undoStack.length > 0){
	    tempUndoStack = undoStack.pop
	    while (tempUndoStack.length > 0){
	      val change = tempUndoStack.pop
	      change.perform(new NullProgressMonitor())
	    }
	  }
	}
}