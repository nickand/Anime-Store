package test.android.testmerlin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thefinestartist.finestwebview.FinestWebView;

import test.android.testmerlin.BaseFragment;
import test.android.testmerlin.R;
import test.android.testmerlin.utils.Constants;


public class AboutMeFragment extends BaseFragment {

    /*public static final String CLASS_TAG = AboutMeFragment.class.getSimpleName();

    private FragmentAboutMeBinding binding;

    public AboutMeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = initViews(inflater, container);

        setListeners();

        return view;
    }

    private View initViews(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_about_me, container, false);

        return binding.getRoot();
    }

    private void setListeners() {
        binding.imageViewGithub.setOnClickListener(this);
        binding.imageViewLinkedIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewGithub:
                new FinestWebView.Builder(getActivity()).show(Constants.GITHUB_URL);
                break;
            case R.id.imageViewLinkedIn:
                new FinestWebView.Builder(getActivity()).show(Constants.LINKEDIN_URL);
                break;
        }
    }*/
}
