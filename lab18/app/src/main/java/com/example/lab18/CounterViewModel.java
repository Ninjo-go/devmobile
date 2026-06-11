package com.example.lab18;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/* 403f2e has created this ViewModel */
public class CounterViewModel extends ViewModel {
private final MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
public CounterViewModel() { countLiveData.setValue(0); }
public void increment() {
Integer c = countLiveData.getValue();
if (c != null) countLiveData.setValue(c + 1);
}
public void decrement() {
Integer c = countLiveData.getValue();
if (c != null) countLiveData.setValue(c - 1);
}
public void reset() { countLiveData.setValue(0); }
public LiveData<Integer> getCount() { return countLiveData; }
}