package jayyo.mymvvminkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jayyo.mymvvminkotlin.model.User
import jayyo.mymvvminkotlin.ui.theme.MyMVVMInKotlinTheme
import jayyo.mymvvminkotlin.viewModel.MainViewModel
import jayyo.mymvvminkotlin.viewModel.MarsViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val marsViewModel: MarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMVVMInKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            UserListScreen(mainViewModel)
                        }

                        Column(
                            modifier = Modifier.align(Alignment.TopCenter)
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            Text(text = marsViewModel.marsUiState)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMVVMInKotlinTheme {
        Greeting("Android")
    }
}

@Composable
fun UserListScreen(viewModel: MainViewModel) {
    val users by viewModel.users.collectAsState()

    Column {
        LazyColumn {
            items(users) { user ->
                UserItem(user)
            }
        }
        Button(onClick = {
            viewModel.addUser(User(users.size + 1, "New User", "newuser@example.com"))
        }) {
            Text(text = "Add User")
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column {
        Text(text = "Name: ${user.name}")
        Text(text = "Email: ${user.email}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyMVVMInKotlinTheme {
        UserItem(User(1, "Preview User", "preview@example.com"))
    }
}