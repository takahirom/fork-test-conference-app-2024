plugins {
    id("droidkaigi.convention.kmpfeature")
}

android.namespace = "io.github.droidkaigi.confsched.feature.sessions"
roborazzi.generateComposePreviewRobolectricTests.packages = listOf("io.github.droidkaigi.confsched.sessions")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.designsystem)
                implementation(projects.core.ui)
                implementation(projects.core.model)

                implementation(libs.composeNavigation)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.uiToolingPreview)
            }
        }
        androidTarget {
            dependencies {
                implementation(libs.composeMaterialWindowSize)
            }
        }
        androidUnitTest {
            dependencies {
                implementation(projects.core.testing)
            }
        }
    }
}
