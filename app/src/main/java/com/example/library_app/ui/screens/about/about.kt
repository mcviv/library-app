package com.example.library_app.ui.screens.about

import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
//import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.RememberObserver
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.library_app.navigations.ROUTE_HOME


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun About(navController: NavController) {
    val mContext = LocalContext.current
    Column(

        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Welcome to Library Management",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Red,
                fontStyle = FontStyle.Italic,

            ),
            modifier = Modifier.fillMaxWidth()
                .padding(top= 40.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Our library management app helps you organize," +
                    " catalog, and manage your library efficiently. " +
                    "With a focus on user experience and functionality, " +
                    "our app is designed to streamline your operations, " +
                    "improve book tracking, and enhance user satisfaction." +
                    "",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "This App is design by Markviv thanks to Emobilis Mobile Technology Institute",style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Mission Statement",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontStyle = FontStyle.Italic,
                color = Color.Red
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Text(
            text = "Our mission is to provide top-notch library management solutions for both small and large institutions, helping you to maintain a well-organized and accessible library for everyone.",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Contact Us",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Red
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        ClickableText(
            text = AnnotatedString("Email: support@librarymanagement.com"),
            style = TextStyle(fontSize = 16.sp, color = Color.Blue),
            onClick = { /* You can add email action here */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Phone: +254 706 314 626",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(10.dp))

        //SMS

        Button(onClick = {

            val smsIntent= Intent(Intent.ACTION_SENDTO)
            smsIntent.data="smsto:0706314626".toUri()
            smsIntent.putExtra("sms_body","Hello ,how was your day?")
            mContext.startActivity(smsIntent)

        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            shape = RoundedCornerShape(10.dp)

        )

        {
            Text(text = "SMS?")
        }
        //sms

        Spacer(modifier = Modifier.height(10.dp))

        //Call

        Button(onClick = { val callIntent=Intent(Intent.ACTION_DIAL)
            callIntent.data="tel:0706314626".toUri()
            mContext.startActivity(callIntent) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            shape = RoundedCornerShape(10.dp)

        )

        {
            Text(text = "CALL")
        }

        //call

        Spacer(modifier = Modifier.height(10.dp))

        //Email
        Button(onClick =    { val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("vmarkviv@gmail.com"))
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body")
            mContext.startActivity(shareIntent) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            shape = RoundedCornerShape(10.dp)

        )

        {
            Text(text = "EMAIL")
        }
        //Email

        Spacer(modifier = Modifier.height(10.dp))

        //camera
        Button(onClick = {
            val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (cameraIntent.resolveActivity(mContext.packageManager)!=null){
                mContext.startActivity(cameraIntent)
            }else{
                println("Camera app is not available")
            } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            shape = RoundedCornerShape(10.dp)

        )

        {
            Text(text = "CAMERA")
        }
        //camera

        Spacer(modifier = Modifier.height(10.dp))


        Button(onClick = {
            val shareIntent=Intent(Intent.ACTION_SEND)
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
            mContext.startActivity(Intent.createChooser(shareIntent, "Share")) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            shape = RoundedCornerShape(10.dp)

        )

        {
            Text(text = "SHARE")
        }

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = { navController.navigate(ROUTE_HOME)},
        ) {
            Text(text = "Back")

        }
    }

}



@Preview(showBackground = true)
@Composable
fun PreviewAbout() {
    About(rememberNavController())
}