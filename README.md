# 🔐 Hooza ( Quiz Game)
Hooza คือ โปรแกรมที่ไว้ใช้สำหรับการสร้างคำถามไว้สำหรับ `Teacher` ในการสร้างคำถามเพื่อทบทวนเนื้อหาที่ได้เรียนมาหรือเนื้อหาที่อยากทำสอบ 

# 🗜️การทำงานของ Hooza?
`Hooza` จะทำงานในวง Local host โดยผู้เป็น `Teacher` จะเป็นผู้สร้างการเชื่อมต่อขึ้นมา และตัว `Student` จะเป็นผู้ที่เชื่อมต่อเข้ามาที่ Lobby

## ภาษาที่ใช้ในการทำ Hooza
<img src="http://www.digitaltrends.com/wp-content/uploads/2010/11/java-logo.jpg" >

### ตัวอย่าง Code ของ การสร้าง Socket ของ Server
```java
public void run(){

        try {
            ss = new ServerSocket(5555);
            System.out.println("Server is running..");
            analysis = new QuestionAnalysis();
            while (!exit){
                client = new Clienthanler(ss.accept());
                client.setQuestionAna(analysis);
                tr = new Thread(client);  //tr stand for Thread
                tr.start();
                list.add(client);
                try{
                    Thread.sleep(10);
                    ta.append(client.getID() + "\n");
                }catch (Exception ex){
                    System.out.println("Thread sleep error :" + ex);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
### ตัวอย่างของโปรแกรม Hooza

# 👥Team Member

|<a href=""><img src="https://scontent.fbkk2-8.fna.fbcdn.net/v/t1.0-9/18156901_1456506974417122_2622418456792260905_n.jpg?_nc_cat=109&_nc_ht=scontent.fbkk2-8.fna&oh=0c174e778166cbfb620e3f7eadd1ea87&oe=5C9609FB" width="100px"></a>  |<a href=""><img src="https://scontent.fbkk2-8.fna.fbcdn.net/v/t1.0-9/35058209_1947809241953557_2314018221763592192_n.jpg?_nc_cat=104&_nc_ht=scontent.fbkk2-8.fna&oh=9cda7e3980e022766409e7ace785f955&oe=5CAA9431" width="100px"></a>  |
| :-: | :-: |
|นายกวีพล ขุนศรี|นายชยากร เทียนขาว|
|60070003 |      60070013      |
|    @Supermalonz    |     @Cyk     |

# 👨‍🏫 Instructor

|<a href=""><img src="https://scontent.fbkk2-7.fna.fbcdn.net/v/t1.0-9/37121505_10156492178048805_2165340472749326336_n.jpg?_nc_cat=111&_nc_ht=scontent.fbkk2-7.fna&oh=9455db85c22726de7279c1b8a0065954&oe=5C930D70" width="100px"></a>|
| :-: |
|ผศ.ดร.ธนิศา นุ่มนนท|
