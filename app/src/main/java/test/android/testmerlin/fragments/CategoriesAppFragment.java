package test.android.testmerlin.fragments;

import test.android.testmerlin.BaseFragment;

public class CategoriesAppFragment extends BaseFragment {

    /*public static final String CLASS_TAG = CategoriesAppFragment.class.getSimpleName();
    private AppsListRecyclerViewAdapter mAdapter;
    private Context mContext;
    private List<Thing> things = new ArrayList<>();
    private OnClickActivityListener mListener;

    private String categoryId;
    private String categorySelected;

    private FragmentAppsCategoryBinding binding;

    public CategoriesAppFragment() {
    }

    public static CategoriesAppFragment newInstance() {
        CategoriesAppFragment fragment = new CategoriesAppFragment();
        return fragment;
    }

    public static CategoriesAppFragment newInstance(Bundle args) {
        CategoriesAppFragment fragment = new CategoriesAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            categoryId = getArguments().getString("category_id");
            categorySelected = getArguments().getString("name_category");

            getPostsRedditFromCategory(categoryId);
        } else {
            categoryId = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initViews(inflater, container);

        setErrorConnectionOrShowCategories();

        setListeners();

        return view;
    }

    private void setErrorConnectionOrShowCategories() {
        if (things.isEmpty()) {

            if (categoryId != null) {
                if (Utils.isNetworkConnected()) {
                    binding.containerProgressIndicator.setVisibility(View.VISIBLE);
                    getPostsRedditFromCategory(categoryId);
                } else {
                    binding.containerProgressIndicator.setVisibility(View.GONE);
                    binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
                }
            } else {
                if (Utils.isNetworkConnected()) {
                    binding.containerProgressIndicator.setVisibility(View.VISIBLE);
                    getPostsRedditFromCategory("0");
                } else {
                    binding.containerProgressIndicator.setVisibility(View.GONE);
                    binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
                }
            }

        } else {
            //mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_BROWSER);
            //binding.postsListsReddit.setAdapter(mAdapter);
        }
    }

    @NonNull
    private View initViews(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_apps_category, container, false);

        View view = binding.getRoot();

        mContext = view.getContext();

        mListener.setTitleToolbar(categorySelected);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.postsListsReddit.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        binding.postsListsReddit.setHasFixedSize(true);
        //mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_BROWSER);
       // binding.postsListsReddit.setAdapter(mAdapter);
        return view;
    }

    private void setListeners() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CategoriesActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(i, MainActivity.RESULT_OK);
            }
        });

        binding.postsListsReddit.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && binding.fab.isShown()) {
                    binding.fab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
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
    }

    private void getPostsRedditFromCategory(String categoryId) {
        ApiClient.ApiInterface apiService = ApiClient.getApiClient().create(ApiClient.ApiInterface.class);
        Call<RedditApiResponse> call = apiService.getPostsFromCategories(categoryId);
        call.enqueue(new Callback<RedditApiResponse>() {
            @Override
            public void onResponse(Call<RedditApiResponse> call, Response<RedditApiResponse> response) {
                RedditApiResponse apiResponse = response.body();

                things = getListOfApplications(apiResponse);

                if (response.isSuccessful()) {
                    binding.containerProgressIndicator.setVisibility(View.GONE);

                   // mAdapter = new AppsListRecyclerViewAdapter(things, mListener, Constants.TYPE_FROM_BROWSER);
                   // binding.postsListsReddit.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RedditApiResponse> call, Throwable t) {
                t.printStackTrace();
                binding.containerNoInternetMessage.setVisibility(View.VISIBLE);
            }
        });

    }

    private List<Thing> getListOfApplications(RedditApiResponse feedWrapper) {
        Listing listing = null;
        List<Thing> things = new ArrayList<>();
        if (feedWrapper != null) {
            listing = feedWrapper.getListing();
        }
        if (listing != null) {
            if (listing.getThings() != null && !listing.getThings().isEmpty()) {
                Log.e(CLASS_TAG, "" + listing.getThings().size());
                things = listing.getThings();
            } else {
                Log.e(CLASS_TAG, "Empty");
            }
        } else {
            Log.e(CLASS_TAG, "Empty listing!");
        }
        return things;
    }*/
}
