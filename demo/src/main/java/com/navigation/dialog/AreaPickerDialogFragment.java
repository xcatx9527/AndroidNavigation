package com.navigation.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.view.WheelOptions;
import com.navigation.R;
import com.navigation.androidx.AwesomeFragment;

public class AreaPickerDialogFragment extends AwesomeFragment {

    public static final String KEY_SELECTED_AREA = "selected_area";

    WheelOptions<String> wheelOptions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_area_picker, container, false);
        root.findViewById(R.id.iv_cancel).setOnClickListener(v -> {
            hideDialog();
        });
        root.findViewById(R.id.tv_finish).setOnClickListener(v -> {
            int[] items = wheelOptions.getCurrentItems();
            String text = areaUtils.getOptions1Items().get(items[0]) + "-"
                    + areaUtils.getOptions2Items().get(items[0]).get(items[1]) + "-"
                    + areaUtils.getOptions3Items().get(items[0]).get(items[1]).get(items[2]);
            Bundle data = new Bundle();
            data.putString(KEY_SELECTED_AREA, text);
            setResult(Activity.RESULT_OK, data);
            hideDialog();
        });

        wheelOptions = new WheelOptions<>(root, true);
        return root;
    }

    AreaUtils areaUtils;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        areaUtils = new AreaUtils(requireContext());
        areaUtils.initJsonData();
        wheelOptions.setCyclic(false);
        wheelOptions.setPicker(areaUtils.getOptions1Items(), areaUtils.getOptions2Items(), areaUtils.getOptions3Items());
    }

}
