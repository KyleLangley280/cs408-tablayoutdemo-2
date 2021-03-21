package edu.jsu.mcis.cs408.tablayoutdemo2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Field;

public class FragmentOne extends Fragment implements TabFragment {

    private final String title = "Calculator";
    private TextView outputField;
    private Calculator calculator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        calculator = new Calculator(this);
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        outputField = view.findViewById(R.id.textView);
        Field[] fields = R.id.class.getFields();
        for (int i = 0; i < fields.length; ++i) {
            String name = fields[i].getName();
            if (name.length() >= 3 && name.substring(0, 3).equals("btn")) {
                int id = getResources().getIdentifier(name, "id", getActivity().getPackageName());
                view.findViewById(id).setOnClickListener(this::onClick);
            }
        }
    }
    public void onClick(View v) {
        String button = ((Button) v).getText().toString();
        calculator.process(button);
        outputField.setText(calculator.getDisplay());
    }

    public String getTabTitle() { return title; }

}