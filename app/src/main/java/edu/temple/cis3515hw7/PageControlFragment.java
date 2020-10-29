package edu.temple.cis3515hw7;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageControlFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;

    private webControlInterface parentActivity;

    public PageControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageControlFragment newInstance(String param1, String param2) {
        PageControlFragment fragment = new PageControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof webControlInterface) {
            parentActivity = (webControlInterface) context;
        } else {
            throw new RuntimeException("You must implement webControlInterface to attach this fragment");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_page_control, container, false);
        v.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                parentActivity.forwardClick();
            }
        });
        v.findViewById(R.id.prev_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                parentActivity.backClick();
            }
        });
        v.findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView text = v.findViewById(R.id.url_input);

                parentActivity.searchClick( text.getText().toString());
            }
        });

        return v;
    }

    public void linkClicked(String html){
        EditText text = v.findViewById(R.id.url_input);
        text.setText(html, TextView.BufferType.EDITABLE);
    }

    interface webControlInterface {
        void searchClick(String html);
        void backClick();
        void forwardClick();
    }
}