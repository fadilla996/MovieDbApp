import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.*

fun DependencyHandler.libraries(){
    androidX()
    androidPaging()
    daggerHilt()
    glide()
    gson()
    gander()
    materialDesign()
    navGraph()
    okHttp()
    retrofit()
    vmLifeCycle()
    googleFirebase()
}