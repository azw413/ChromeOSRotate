# ChromeOSRotate
Android App to rotate orientation on Chrome Tablets. Particularly useful for when you are running ChromeOS on a non-Chromebook tablet where the rotation sensor (or accelerometer) are not detected by the Linux kernel and ChromeOS. 

# How it works
ChromeOS's Android compatibility feature must query an activity's required orientation when it is launched and forces the tablet rotation to match the activity. Conveniently, it leaves the tablet in that orientation when the app or activity finishes. 

This app exploits that behaviour by defining two activities: PortraitActivity and LandscapeActivity with the orientation forced appropriately in the manifest. When the app launches it starts MainActivity which, after rendering, measures the width / height to work out the current orientation (other methods seem unreliable under ChromeOS). It then launches the rotated orientation activity via an intent. Once the rotated activity is displayed, it then finishes the activity and exits the JVM.