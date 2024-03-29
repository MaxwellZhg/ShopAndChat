package shopandclient.ssf.com.shopandclient.swipe;




/**
 *interface for {@link SwipeBackActivity} and {@link SwipeBackPreferenceActivity}
 */
public interface SwipeBackActivityBase {
    /**
     *the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();


    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
