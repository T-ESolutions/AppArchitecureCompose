package app.te.architecture.presentation.base.events


interface BaseContentEventListener : BaseEventListener {
    fun changeSubCategoryItem(itemId: Int, currentPosition: Int)
    fun openContent(itemId: Int, content: String)
    fun showSubscribeDialog(direction: Int)

}