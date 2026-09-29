# StudentInfo_2415141122122

## Thông tin sinh viên

- **MSSV:** 2415141122122
- **Họ tên:** Nguyen Nhuan Tri
- **Tên Project:** StudentInfo_2415141122122

## Model

Project sử dụng Data Class `Student` để lưu trữ thông tin sinh viên.

Model gồm các thuộc tính:

- `id`: Mã số sinh viên
- `name`: Họ và tên
- `className`: Lớp
- `age`: Tuổi
- `score`: Điểm
- `status`: Trạng thái
- `phone`: Số điện thoại

Model:

```kotlin
data class Student(
    val id: String,
    val name: String,
    val className: String,
    val age: Int,
    val score: Double,
    val status: String,
    val phone: String
)