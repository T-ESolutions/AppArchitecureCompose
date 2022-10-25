package app.te.architecture.presentation.contactus

import app.te.architecture.presentation.base.events.BaseEventListener

interface ContactUsEventListeners : BaseEventListener {
  fun openContactUrl(url: String)
}