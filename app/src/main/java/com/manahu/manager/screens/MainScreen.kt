package com.manahu.manager.screens

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manahu.manager.R
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.core.content.edit
import androidx.core.net.toUri

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current as Activity
    val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

     BackHandler {
        context.finishAffinity()
    }

    var selectedImage by remember { mutableStateOf<Uri?>(prefs.getString("selectedImageUri", null)?.toUri()) }
    val showDialogName = remember { mutableStateOf(false) }
    val showDialogAge = remember { mutableStateOf(false) }
    var isAgeSet by remember { mutableStateOf(prefs.getBoolean("isAgeSet", false)) }
    var age by remember { mutableStateOf(prefs.getString("age", "18") ?: "18") }
    var name by remember { mutableStateOf(prefs.getString("name", "Игрок") ?: "Игрок") }
    fun getAgeString(age: Int): String {
        val lastDigit = age % 10
        val lastTwoDigits = age % 100

        if (lastTwoDigits in 11..19) {
            return "лет"
        }
        return when (lastDigit) {
            1 -> "год"
            in 2..4 -> "года"
            else -> "лет"
        }
    }

    LaunchedEffect(key1 = isAgeSet) {
        if (!isAgeSet) {
            showDialogAge.value = true
        }
    }

    // Пример кода для кнопки "Новая игра"
//prefs.edit {
//    putBoolean("isAgeSet", false)
//    // тут же можно сбросить и другие данные, например, имя
//    // putString("name", "Игрок")
//}
// После этого можно перезапустить экран или навигироваться на него заново


     Box(modifier = Modifier.fillMaxSize().systemBarsPadding(), contentAlignment = Alignment.Center) {
         Image(painter = painterResource(R.drawable.fonpleten), contentDescription = "fon",
             modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds
         )
         Column(modifier = Modifier.fillMaxSize().systemBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Top) {
             Spacer(modifier = Modifier.height(16.dp))
             Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement = Arrangement.SpaceBetween){
                 Row(verticalAlignment = Alignment.CenterVertically){
                     Image(painter = painterResource(R.drawable.money), contentDescription = "money",
                          modifier = Modifier.width(50.dp).height(30.dp), contentScale = ContentScale.FillBounds
                     )
                     Spacer(modifier = Modifier.width(16.dp))
                     Text(text = "1234567890", fontSize = 20.sp, color = colorResource(R.color.green),
                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny))
                     )
                 }
                 Row(verticalAlignment = Alignment.CenterVertically){
                     val ageInt = age.toIntOrNull() ?: 18
                     val ageSuffix = getAgeString(ageInt)
                     Text(text = "$age $ageSuffix", fontSize = 16.sp, color = colorResource(R.color.green),
                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                          modifier = Modifier.clickable(enabled = !isAgeSet){ showDialogAge.value = true }
                     )
                     if (showDialogAge.value) {
                         AlertDialog(
                             onDismissRequest = { if (isAgeSet) { showDialogAge.value = false } },
                             containerColor = colorResource(id = R.color.white),
                             text = {
                                 Column(modifier = Modifier.fillMaxWidth().height(200.dp),
                                     horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                                     Text(text = "Сколько Вам лет?", fontSize = 24.sp, color = colorResource(R.color.green),
                                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                                     )
                                     OutlinedTextField(value = age, onValueChange = { newValue -> age = newValue },
                                         placeholder = { Text("Введите свой возраст", color = colorResource(R.color.green)) },
                                         textStyle = TextStyle(color = colorResource(R.color.green), fontSize = 20.sp),
                                         modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(
                                             colorResource(R.color.white), RoundedCornerShape(8.dp)),
                                         colors = TextFieldDefaults.outlinedTextFieldColors(
                                             focusedBorderColor = colorResource(R.color.green),
                                             unfocusedBorderColor = colorResource(R.color.green),
                                             cursorColor = colorResource(R.color.green),
                                             placeholderColor = colorResource(R.color.green),
                                             textColor = colorResource(R.color.green)
                                         ),
                                         shape = RoundedCornerShape(8.dp)
                                     )
                                     Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                                         Image(painter = painterResource(R.drawable.butcancel), contentDescription = "butcancel",
                                              modifier = Modifier.width(130.dp).height(50.dp).clickable{
                                                 if (isAgeSet) {
                                                      showDialogAge.value = false
                                                  }
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                         Image(painter = painterResource(R.drawable.butyes), contentDescription = "butyes",
                                              modifier = Modifier.width(70.dp).height(50.dp).clickable{
                                                  prefs.edit {
                                                      putString("age", age)
                                                      putBoolean("isAgeSet", true)
                                                  }
                                                  isAgeSet = true
                                                  showDialogAge.value = false
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                     }
                                 }
                             },
                             confirmButton = {},
                             dismissButton = {})
                     }
                     Column(horizontalAlignment = Alignment.CenterHorizontally) {
                         PlayerPhotoSectionResult(
                             initialImageUri = selectedImage, onImageSelected = {
                                 selectedImage = it
                                 prefs.edit { putString("selectedImageUri", it.toString()) }
                             })
                         Spacer(modifier = Modifier.height(8.dp))
                         Text(text = name, fontSize = 16.sp, color = colorResource(R.color.green),
                             fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                             modifier = Modifier.clickable{ showDialogName.value = true }, textAlign = TextAlign.Center
                         )
                         if (showDialogName.value) {
                             AlertDialog(
                                 onDismissRequest = { showDialogName.value = false },
                                 containerColor = colorResource(id = R.color.white),
                                 text = {
                                   Column(modifier = Modifier.fillMaxWidth().height(200.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly){
                                     Text(text = "Ваше имя?", fontSize = 24.sp, color = colorResource(R.color.green),
                                         fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.coiny)),
                                     )
                                     OutlinedTextField(value = name, onValueChange = { newValue -> name = newValue },
                                         placeholder = { Text("Введите свое имя", color = colorResource(R.color.green)) },
                                         textStyle = TextStyle(color = colorResource(R.color.green), fontSize = 20.sp),
                                         modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(
                                             colorResource(R.color.white), RoundedCornerShape(8.dp)),
                                         colors = TextFieldDefaults.outlinedTextFieldColors(
                                             focusedBorderColor = colorResource(R.color.green),
                                             unfocusedBorderColor = colorResource(R.color.green),
                                             cursorColor = colorResource(R.color.green),
                                             placeholderColor = colorResource(R.color.green),
                                             textColor = colorResource(R.color.green)
                                         ),
                                         shape = RoundedCornerShape(8.dp)
                                     )
                                     Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                                         verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
                                         Image(painter = painterResource(R.drawable.butcancel), contentDescription = "butcancel",
                                              modifier = Modifier.width(130.dp).height(50.dp).clickable{
                                                showDialogName.value = false
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                         Image(painter = painterResource(R.drawable.butyes), contentDescription = "butyes",
                                              modifier = Modifier.width(70.dp).height(50.dp).clickable{
                                                  prefs.edit { putString("name", name) }
                                                showDialogName.value = false
                                              }, contentScale = ContentScale.FillBounds
                                         )
                                     }
                                 }
                                 },
                                 confirmButton = {},
                                 dismissButton = {})
                         }
                     }
                 }
             }
             Spacer(modifier = Modifier.height(16.dp))

         }
     }
}


@Composable
fun PlayerPhotoSectionResult(initialImageUri: Uri?, onImageSelected: (Uri?) -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var selectedImageUri by remember { mutableStateOf(initialImageUri) }

     LaunchedEffect(initialImageUri) {
        if (initialImageUri != null && initialImageUri.toString() == "empty") {
            selectedImageUri = null
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
            val savedUri = saveImageToFile(context, it)
            selectedImageUri = savedUri
            onImageSelected(savedUri)
        } ?: run {
            selectedImageUri = null
            onImageSelected(null)
        }
        }
    )
    val legacyPhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
            val savedUri = saveImageToFile(context, it)
            selectedImageUri = savedUri
            onImageSelected(savedUri)
        } ?: run {
            selectedImageUri = null
            onImageSelected(null)
        }
        }
    )
    val launchPhotoPicker = {
        scope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            } else {
                legacyPhotoPickerLauncher.launch("image/*")
            }
        }
    }
        if (selectedImageUri != null) {
            AsyncImage(model = ImageRequest.Builder(context).data(selectedImageUri).crossfade(true).build(),
                contentDescription = "Selected photo",
                modifier = Modifier.size(40.dp).clip(CircleShape).clickable { launchPhotoPicker() },
                contentScale = ContentScale.Crop
            )
        } else {
            Image(painter = painterResource(R.drawable.playericon), contentDescription = "User photo placeholder",
                modifier = Modifier.size(40.dp).clip(CircleShape).clickable { launchPhotoPicker() },
                contentScale = ContentScale.FillBounds
            )
        }
}
fun saveImageToFile(context: Context, uri: Uri): Uri {
    val inputStream = context.contentResolver.openInputStream(uri)
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir: File = File(context.getExternalFilesDir(null), "images")
    if (!storageDir.exists()) {
        storageDir.mkdirs()
    }
    val photoFile = File(storageDir, "JPEG_${timeStamp}.jpg")
    if (!photoFile.exists()) {
        val outputStream = FileOutputStream(photoFile)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
    }
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", photoFile)
}
