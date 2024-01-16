import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList;
    private TaskAdapter taskAdapter;
    private EditText taskEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        taskEditText = findViewById(R.id.taskEditText);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    private void addTask() {
        String taskName = taskEditText.getText().toString().trim();

        if (!taskName.isEmpty()) {
            Task newTask = new Task(taskName);
            taskList.add(newTask);
            taskAdapter.notifyDataSetChanged();
            taskEditText.setText("");
        } else {
            Toast.makeText(this, "Task name cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}