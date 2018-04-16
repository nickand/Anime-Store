package test.android.testmerlin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        checkNetworkConnection();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void checkNetworkConnection() {
        ReactiveNetwork.observeNetworkConnectivity(getActivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override
                    public void accept(final Connectivity connectivity) {
                        if (!connectivity.isAvailable()) {
                            SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_STANDARD)
                                    .setText(getString(R.string.error_network))
                                    .setDuration(Style.DURATION_LONG)
                                    .setFrame(Style.FRAME_LOLLIPOP)
                                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                                    .setAnimations(Style.ANIMATIONS_POP).show();
                        }
                    }
                });
    }
}
