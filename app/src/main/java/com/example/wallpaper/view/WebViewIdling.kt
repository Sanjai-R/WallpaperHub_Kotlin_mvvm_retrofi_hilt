import androidx.test.espresso.IdlingResource

class AppInitializationIdlingResource : IdlingResource {
    private var callback: IdlingResource.ResourceCallback? = null
    private var isIdle = false


    override fun getName(): String = "AppInitializationIdlingResource"

    override fun isIdleNow(): Boolean = isIdle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    fun setIdleState(isIdleNow: Boolean) {
        isIdle = isIdleNow
        if (isIdleNow && callback != null) {
            callback!!.onTransitionToIdle()
        }
    }
}