package br.com.rotasescolar.ui.activity;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.rotasescolar.R;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.presenter.RegisterStudentPresenter;
import br.com.rotasescolar.utils.Constants;

/**
 * Created by gustavosantorio on 07/08/17.
 */

public class RegisterStudentActivity extends BaseActivity implements LifecycleRegistryOwner{


    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private RegisterStudentPresenter presenter;

    private TextInputLayout ilStudentName;
    private EditText etStudentName;
    private TextInputLayout ilParentName;
    private EditText etParentName;
    private TextInputLayout ilParentNumber;
    private EditText etParentNumber;
    private TextInputLayout ilZipcode;
    private EditText etZipcode;
    private TextInputLayout ilStreet;
    private EditText etStreet;
    private TextInputLayout ilNumber;
    private EditText etNumber;
    private TextInputLayout ilComplement;
    private EditText etComplement;
    private TextInputLayout ilNeigborhood;

    public Integer studentId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register_student);
        super.onCreate(savedInstanceState);

        if(getIntent().getExtras() != null)
            studentId = getIntent().getExtras().getInt(Constants.RegisterStudentConstants.STUDENT_ID);

        presenter =  new RegisterStudentPresenter(this);
        getLifecycle().addObserver(presenter);
        inflateView();
    }

    private void inflateView(){
        ilStudentName = viewById(R.id.il_student_name);
        etStudentName = viewById(R.id.et_student_name);
        ilParentName = viewById(R.id.il_parent_name);
        etParentName = viewById(R.id.et_parent_name);
        ilParentNumber = viewById(R.id.il_parent_number);
        etParentNumber = viewById(R.id.et_parent_number);
        ilZipcode = viewById(R.id.il_zipcode);
        etZipcode = viewById(R.id.et_zipcode);
        ilStreet = viewById(R.id.il_street);
        etStreet = viewById(R.id.et_street);
        ilNumber = viewById(R.id.il_number);
        etNumber = viewById(R.id.et_number);
        ilComplement = viewById(R.id.il_complement);
        etComplement = viewById(R.id.et_complement);
        ilNeigborhood = viewById(R.id.il_neigborhood);
    }

    public void setEditMode(Student student){
        etStudentName.setText(student.getName());
        etParentName.setText(student.getParentName());
        etParentNumber.setText(student.getPhoneNumber());
        etZipcode.setText(student.getZipcode());
        etStreet.setText(student.getAddress());
        etNumber.setText(student.getAddressNumber());
        etComplement.setText(student.getComplement());
    }

    public void setDefaultMode(){

    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
