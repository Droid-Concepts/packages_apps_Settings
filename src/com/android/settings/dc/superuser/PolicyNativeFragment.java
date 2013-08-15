/*
 * Copyright (C) 2013 Droid Concepts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.dc.superuser;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.settings.Utils;
import com.koushikdutta.superuser.LogNativeFragment;
import com.koushikdutta.superuser.PolicyFragmentInternal;
import com.koushikdutta.superuser.SettingsNativeFragment;

public class PolicyNativeFragment extends com.koushikdutta.superuser.PolicyNativeFragment {
    public static class CMLogNativeFragment extends LogNativeFragment {
        @Override
        public View onCreateView(LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            adjustListPadding(getInternal().getListView());
            return view;
        }
    }

    public static class CMSettingsNativeFragment extends SettingsNativeFragment {
        @Override
        public View onCreateView(LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            adjustListPadding(getInternal().getListView());
            return view;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Utils.forcePrepareCustomPreferencesList(container, view, getInternal().getListView(), false);
        return view;
    }

    @Override
    public PolicyFragmentInternal createFragmentInterface() {
        return new FragmentInternal(this) {
            @Override
            protected LogNativeFragment createLogNativeFragment() {
                return new CMLogNativeFragment();
            }

            @Override
            protected SettingsNativeFragment createSettingsNativeFragment() {
                return new CMSettingsNativeFragment();
            };
        };
    }

    private static void adjustListPadding(ListView list) {
        final Resources res = list.getResources();
        final int paddingSide = res.getDimensionPixelSize(
                com.android.internal.R.dimen.preference_fragment_padding_side);
        final int paddingBottom = res.getDimensionPixelSize(
                com.android.internal.R.dimen.preference_fragment_padding_bottom);

        list.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        list.setPadding(paddingSide, 0, paddingSide, paddingBottom);
    }
}
