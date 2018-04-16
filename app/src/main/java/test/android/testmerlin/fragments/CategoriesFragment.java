package test.android.testmerlin.fragments;

import android.support.v4.app.Fragment;

import test.android.testmerlin.BaseFragment;

public class CategoriesFragment extends BaseFragment {
    @org.jetbrains.annotations.Nullable
    public static Fragment newInstance() {
        return null;
    }

   /* public static final String CLASS_TAG = CategoriesFragment.class.getSimpleName();
    private CategoriesListRecyclerViewAdapter mAdapter;
    private Context mContext;
    private OnClickActivityListener mListener;

    private FragmentCategoryBinding binding;

    public CategoriesFragment() {
    }

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initViews(inflater, container);
    }

    @NonNull
    private View initViews(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_category, container, false);

        mListener.setTitleToolbar(getString(R.string.category));

        View view = binding.getRoot();

        mContext = view.getContext();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.categoriesListsReddit.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        binding.categoriesListsReddit.setHasFixedSize(true);

        setCategoriesList();

        return view;
    }

    private void setCategoriesList() {
        if (Category.getCategories(getActivity()).isEmpty()) {
            Category.getCategories(getActivity());
        } else {
            mAdapter = new CategoriesListRecyclerViewAdapter(Category.getCategories(getActivity()), mListener);
            binding.categoriesListsReddit.setAdapter(mAdapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickActivityListener) {
            mListener = (OnClickActivityListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnClickActivityListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/
}
