package data;

import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Car;
import domain.Student;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FetchData {

public static List<Car> getCarList() throws IOException {
    InputStream in = Resources.getResource("cars.json").openStream();
    String json = IOUtils.toString(in, StandardCharsets.UTF_8);
    Type listType  = new TypeToken<ArrayList<Car>>(){
    }.getType();
    return new Gson().fromJson(json,listType);
}
    public static List<Student> getStudentList() throws IOException {
        InputStream in = Resources.getResource("students.json").openStream();
        String json = IOUtils.toString(in, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<Student>>() {}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();

        return gson.fromJson(json, listType);
    }


}
