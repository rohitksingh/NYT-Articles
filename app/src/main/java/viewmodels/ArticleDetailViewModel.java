package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArticleDetailViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isPageLoadingLiveData;

    public ArticleDetailViewModel(){
        isPageLoadingLiveData = new MutableLiveData<>(true);
    }

    public LiveData<Boolean> getIsPageLoadingLiveData() {
        return isPageLoadingLiveData;
    }

    public void setIsPageLoadingLiveData(boolean isPageLoading){
        isPageLoadingLiveData.setValue(isPageLoading);
    }

}
