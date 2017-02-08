package g7.zcf.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import g7.zcf.R;

/**
 * Create by zcf on 2017/1/22 0022 14:18
 */
public class TitleFragment extends Fragment {
    private String Tag = "zcf_fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(Tag, "fragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(Tag, "fragment onCreateView");
        View view = inflater.inflate(R.layout.titleview, container, false);
        ((TextView) view.findViewById(R.id.titleText)).setText("Title");
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(Tag, "fragment onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        Log.d(Tag, "fragment onStart");
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(Tag, "fragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.d(Tag, "fragment onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(Tag, "fragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(Tag, "fragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(Tag, "fragment onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(Tag, "fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d(Tag, "fragment onDetach");
        super.onDetach();
    }
}
