package viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArticleDetailViewModel extends ViewModel {

    private MutableLiveData<Boolean> isPageLoadingLiveData;
    private MutableLiveData<Integer> loadingStatusLiveData;

    public ArticleDetailViewModel(){
        isPageLoadingLiveData = new MutableLiveData<>(true);
        loadingStatusLiveData = new MutableLiveData<>(0);
    }

    public LiveData<Boolean> getIsPageLoadingLiveData() {
        return isPageLoadingLiveData;
    }

    public void setIsPageLoadingLiveData(boolean isPageLoading){
        isPageLoadingLiveData.setValue(isPageLoading);
    }

    public LiveData<Integer> getLoadingStatusLiveData(){
        return loadingStatusLiveData;
    }

    public void setLoadingStatusLiveData(int status){
        loadingStatusLiveData.setValue(status);
    }
}
