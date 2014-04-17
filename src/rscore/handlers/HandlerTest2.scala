package rscore.handlers

import ch.ethz.resynth.handlers.ReSynthRefactor
import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.handlers.HandlerUtil

class HandlerTest2 extends AbstractHandler {
	def execute(event: ExecutionEvent): Object = {

		val handler = new ReSynthRefactor
		handler.execute(event)

	    return null
	}
}