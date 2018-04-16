package test.android.testmerlin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkNetworkConnection();
    }

    protected void checkNetworkConnection() {
        ReactiveNetwork.observeNetworkConnectivity(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Connectivity>() {
                    @Override
                    public void accept(final Connectivity connectivity) {
                        if (!connectivity.isAvailable()) {
                            SuperActivityToast.create(BaseActivity.this, new Style(), Style.TYPE_STANDARD)
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
