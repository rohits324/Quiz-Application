////package com.quizapp.college.model;
////
////import jakarta.persistence.Column;
////import lombok.Data;
////
////@Data
////public class QuestionWrapper {
////
////    private Integer id;
////
////    @Column(name = "question_title") // Added explicit column mapping
////    private String questionTitle;
////
////    @Column(name = "option_1") // Adjusted column names for consistency
////    private String option1;
////
////    @Column(name = "option_2")
////    private String option2;
////
////    @Column(name = "option_3")
////    private String option3;
////
////    @Column(name = "option_4")
////    private String option4;
////
////    public QuestionWrapper(String questionTitle, Integer id, String option1, String option2, String option3, String option4) {
////        this.questionTitle = questionTitle;
////        this.id = id;
////        this.option1 = option1;
////        this.option2 = option2;
////        this.option3 = option3;
////        this.option4 = option4;
////    }
////}
//
//// QuestionWrapper.java

package com.quizapp.college.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionWrapper {
    private String questionTitle;
    //private Integer id;
   // private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer id;

    public QuestionWrapper(String questionTitle, Integer id, String option1, String option2, String option3, String option4) {
        this.questionTitle = questionTitle;
        //this.id = id;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.id = id;
    }
}

