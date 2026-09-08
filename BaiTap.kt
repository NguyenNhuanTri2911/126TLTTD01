data class Student (
    val studentID : String ,
    val fullName : String ,
    val age : Int ,
    val major : String ,
    val GPA : Double
)
fun addStudent(students : MutableList<Student>){
    print("Nhap vao so sinh vien can them vao  : ")
    val n = readln().toInt()
    for (i in 1..n)
    {
        print("Nhập vào ID sinh viên : ")
        val studentID = readln()
        print("Nhập vào tên sinh viên : ")
        val fullName = readln()
        print("Nhập vào tuổi của sinh viên : ")
        val age = readln().toInt()
        print("Nhập vào thông tin chuyên ngành : ")
        val major = readln()
        print("Nhập vào điểm của sinh viên : ")
        val GPA = readln().toDouble()
        val student = Student(
            studentID,
            fullName,
            age,
            major,
            GPA
        )
        students.add(student)
    }
    println("Đã nhập thành công $n sinh viên ")
}

fun displayAllStudent(students : MutableList<Student>){
    if (students.isEmpty()){
        println("Danh sách sinh viên đang trống ")
        return
    }
    while(true){
        println("========== Hien thi sinh vien ==========")
        println("1. Toan bo sinh vien ")
        println("2. 3 sinh vien co GPA cao nhat ")
        println("0.exit ")
        val choice = readln().toInt()
        when(choice){
            1 -> {
                println("======================Danh sach sinh vien =========================== ")
                for (student in students ){
                 println("ID: ${student.studentID} | Ten: ${student.fullName} | Tuoi: ${student.age} | Nganh: ${student.major} | GPA: ${student.GPA}")
                }
            }
            2 -> {
                println("======================Danh sach sinh vien nam trong top 3 =========================== ")
                val top3 = students.sortedByDescending { it.GPA }.take(3)
                top3.forEach { println("ID: ${it.studentID} | Ten: ${it.fullName} | Tuoi: ${it.age} | Nganh: ${it.major} | GPA: ${it.GPA}") }
            }
            0 -> return
            else -> print("Khong hop le ")
        }
    }
}

fun searchStudent(students : MutableList<Student>){
    while(true){
        println("======= Tim Kiem sinh vien theo cach nao ======")
        println("1. Tim kiem theo do tuoi ")
        println("2. Tim kiem theo khoang GPA ")
        println("3. Tim kiem theo nganh ")
        println("4. Tim kiem theo mot phan ten ")
        println("5. Tim kiem theo theo ma sinh vien ")
        println("0. exit")
        val choice = readln().toInt()
        when (choice) {
            1 -> {
                val tuoiStudent = students.maxByOrNull { it.age }
                print("Sinh vien co tuoi lon nhat la : ${tuoiStudent?.fullName} - GPA : ${tuoiStudent?.age}")
            }

            2 -> {
                print("Nhap vao GPA thap nhat : ")
                val min = readln().toDouble()
                print("Nhap vao GPA cao nhat : ")
                val max = readln().toDouble()
                val result = students.filter { it.GPA in min..max }
                result.forEach { println("${it.fullName} - GPA ${it.GPA}") }
            }

            3 -> {
                print("Nhap vao nganh muon tim kiem : ")
                val major = readln()
                val result = students.filter { it.major.equals(major, ignoreCase = true) }
                result.forEach { println("${it.fullName} - Nganh ${it.major}") }
            }

            4 -> {
                print("Nhap vao ten muon tim : ")
                val keyword = readln()
                val result = students.filter { it.fullName.contains(keyword, ignoreCase = true) }
                result.forEach { println("${it.fullName} - maSinhvien ${it.studentID}") }
            }

            5 -> {
                print("Nhap vao ma sinh vien muon tim ")
                val id = readln()
                val student = students.find { it.studentID == id }
                if (student == null)
                    println("Khong co sinh vien giong ma nay ")
                else
                    println("${student.fullName} - ID ${student.studentID}}")
            }
            0 -> return
            else -> println("Lua chon khong hop le ")
        }
    }
}

fun  calculateAverageGPA(students: MutableList<Student>){
    while(true){
        println("========= Cac cach muon lam ========")
        println("1. Dem so sinh vien co GPA trong khoang nhat dinh ")
        println("2. Tinh GPA trung binh theo nganh ")
        println("3. Sap xep sinh vien theo GPA ")
        println("4. Sap xep sinh vien theo ten ")
        println("5. Sap xep sinh vien theo tuoi ")
        println("0. exit")
        val choice = readln().toInt()
        when(choice){
            1 -> {
                print("Nhap vao khoang GPA thap nhat ")
                val min = readln().toDouble()
                print("Nhap vao khoang GPA cao nhat")
                val max = readln().toDouble()
                val count = students.count{ it.GPA in min .. max}
                println("So sinh co trong khoang GPA tu $min - $max la $count")
            }
            2 -> {
                print("Nhap nganh can tinh GPA ")
                val major = readln()
                val studentInMajor = students.filter{it.major.equals(major, ignoreCase = true)}
                if(studentInMajor.isEmpty()){
                    println("Khong co sinh vien nao torng nganh $major ")
                }
                else{
                    val avg = studentInMajor.map { it.GPA }.average()
                    println("Diem trung binh cua $major la : $avg")
                }
            }
            3 -> {
                val sort = students.sortedByDescending { it.GPA }
                sort.forEach { println("Ten ${it.fullName} - GPA: ${it.GPA}") }
            }
            4 -> {
                val sort = students.sortedBy{ it.fullName.trim().split(" ").last() }
                sort.forEach { println("${it.fullName} ")}
            }
            5 -> {
                val sort = students.sortedBy { it.age }
                sort.forEach {println("Ten ${it.fullName} - Tuoi ${it.age}")}
            }
            0 -> return
            else -> println("Khong hop le")
        }
    }
}
fun findStudent(students : MutableList<Student>){
    val topStudent = students.maxByOrNull{ it.GPA }
    println("Sinh vien co diem GPA cao nhat la : ${topStudent?.fullName} - GPA : ${topStudent?.GPA}")
}
fun removeStudent(students : MutableList<Student>){
    print("Nhap vao ma sinh vien can xoa : ")
    val id = readln()
    val removed = students.removeIf{it.studentID == id}
    if (removed) {
        println("Da xoa thanh cong sinh vien co ma $id")
    }
    else {
        println("Khong tim thay sinh vien co ma $id ")
    }
}
fun main() {
    val students = mutableListOf<Student>(
        Student("2415141122122", "Nguyen Nhuan Tri ", 19, "SPCN", 3.24),
        Student("2415141122123", "Van Thanh Hoang ", 22, "CNTT", 3.16),
        Student("2415141122124", "Mai Tuan Anh ", 21, "Co Khi", 3.02),
        Student("2415141122125", "Nguyen Phu Loc ", 20, "Xay dung", 3.11),
        Student("2415141122126", "Phan Minh Anh Tuan ", 20, "Hoa hoc va moi truong", 3.33)
    )
    while(true){
        println("============ Students Management =========")
        println("1. Add student ")
        println("2. Display all students ")
        println("3. Search students ")
        println("4. Caculate average GPA ")
        println("5. Find student with highest GPA ")
        println("6. Remove student ")
        println("0 .Exit ")
        val choice = readln().toInt()
        when(choice){
            1 -> addStudent(students)
            2 -> displayAllStudent(students)
            3 -> searchStudent(students)
            4 -> calculateAverageGPA(students)
            5 -> findStudent(students)
            6 -> removeStudent(students)
            0 -> return
            else -> println("Lua chon khong hop le ")
        }
    }
}