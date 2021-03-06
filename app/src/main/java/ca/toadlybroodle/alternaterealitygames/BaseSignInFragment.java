package ca.toadlybroodle.alternaterealitygames;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BaseSignInFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FuckBaseSignInFrag";

    private FragmentActivity parActiv;

    private ProgressDialog mProgressDialog;

    // Define the listener of the interface type
    // listener will the activity instance containing fragment
    private FragmentListenerInterface listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // get reference to parent activity for later use
        parActiv = super.getActivity();

        return inflater.inflate(R.layout.fragment_base_signin, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup any handles to view objects here

        parActiv.findViewById(R.id.base_google_sign_in_button).setOnClickListener(this);
        parActiv.findViewById(R.id.base_email_sign_in_button).setOnClickListener(this);

    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListenerInterface) {
            listener = (FragmentListenerInterface) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentListenerInterface");
        }
    }

    @Override
    public void onClick(View v) {
        listener.onFragmentButtonPushed(v);
    }

    protected void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(parActiv);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
    }

}
