package com.link.cloud.api.dataSourse;

import java.util.List;

/**
 * Created by 49488 on 2018/10/23.
 */

public class CoachInfo {

    /**
     * coachName : 贾
     * fitnessCourseType : 1
     * address : 深圳市宝安区
     * storeCoachHeadimg : http://www.handyfitness.com.cn:8094/api/file/uploadfile/img/1540200682958.png
     * planBegtime : 2018-10-23 08:45:00
     * num : 0
     * fitnessCourseContext : {"fitnessCourseintroduce":"哈他瑜伽又名传统瑜伽,瑜伽体系中最实用的一个体系。在哈他( Hatha)这个词中,\u201c哈\u201d(ha)的意思是太阳,\u201c他\u201d(tha)的意思是月亮。它代表男与女,日与夜,阴与阳,冷与热,以及其他任何相辅相成的两个对立面的平衡。练习过程有简单的唱诵,拜日,调息,体式和大休息术","trainingEffect":"哈他瑜伽体系从体位姿势开始练习，这一点与传统的\u201c八分支法\u201d不同，因此也被称为\u201c六分支法瑜伽\u201d。哈他瑜伽主要练习如何控制身体和呼吸，更深一层的效果是使身体各机能有序运转，从而使心灵获得宁静，变得祥和","SuitablePopulation":"哈他瑜伽最适合初学者，是通过身体的姿势、呼吸和放松的技巧，来达到训练的目的。对神经系统、各种腺体和内脏都大有益处，其目的在于推动有节奏的呼吸和开发身体潜能。哈他瑜伽的动作讲究舒适度和呼吸控制，也适合初级学员练习","Reminder":[{"text":"1.运动装备:一身贴身有弹性的舒适瑜伽服+瑜伽裤是在你上课 之前必备物品。在挑选瑜伽服的时候\u2019要尽量选择具有良好吸湿 排汗功能丶包覆性能好的布料,并且穿上之后感觉收身而不紧 身,不会岀现紧勒感最佳。瑜伽垫丶瑜伽砖及瑜伽铺巾也是较为 常用的瑜伽装备。 Handy的瑜伽课程均会为每个学员 免费提供材质健康安全的瑜伽垫及其他所需装备。","type":"text"},{"text":"2.饮食:瑜伽运动要求练习者在练习之前必须空腹,但是空腹 并不等于饥饿,过于饥饿时人体血糖水平大幅度下降,反而不利 于练习。在练习之前的进餐请尽量少吃\u2019并选择低糖食品,辛 辣丶垃圾食品及碳酸充气类的饮料在练习瑜伽之前使绝对禁止食 用的。可选择酸奶丶水果或谷物来补充所需能量","type":"text"},{"text":"3.本地提供更衣室\u2022储物柜\u2022洗手间\u2022淋浴间（储物柜第一个小时免费，第2个小时开始每小时1块钱）","type":"text"},{"text":"4.课程开始前6小时取消，不支持退款。","type":"text"},{"text":"5.内设无限wf网络尽情使用。密码：HANDY20180801","type":"text"},{"text":"重要：成功预约本课程即表示已经阅读并同意（汗的运动注意事项及免责声明）","type":"text"}]}
     * courseReleaseIsplan : 1
     * storeCoachIntroduce : {"goodCourse":[{"text":"","type":"text","goods":"美腹训练"},{"text":"","type":"text","goods":"减脂塑形"}],"certificateList":[{"text":"中国健美协会国家级私人教练","type":"text"},{"text":"中国健美协会产后康复师","type":"text"}]}
     * cocahId : icxll5ai
     * planEndtime : 2018-10-23 09:00:00
     * courseReleaseFlag : 减肥,增肌
     * storeCoachSynopsis : 擅长跑步」「举铁」，而现在提到健身，大家想到的都是「HIIT」「Tabata」, HIIT 到底是什么？它为何能风靡世界甚至成为健身的代名词？今天百科君就带你认识一下 HIIT
     * fitnessCourseName : 测试瑜伽团课
     * storeCoachTopimg : http://www.handyfitness.com.cn:8094/api/file/uploadfile/img/1540200686358.png
     * cocahSex : 1
     * cocahNikename : 贾
     * fitnessCourseCoverimg : http://www.handyfitness.com.cn:8094/api/file/uploadfile/img/1539940676629.png
     * cocahAge : 22
     * courseReleaseMoney : 0.01
     */

    private String coachName;
    private int fitnessCourseType;
    private String address;
    private String storeCoachHeadimg;
    private String planBegtime;
    private int num;
    private FitnessCourseContextBean fitnessCourseContext;
    private int courseReleaseIsplan;
    private StoreCoachIntroduceBean storeCoachIntroduce;
    private String cocahId;
    private String planEndtime;
    private String courseReleaseFlag;
    private String storeCoachSynopsis;
    private String fitnessCourseName;
    private String storeCoachTopimg;
    private int cocahSex;
    private String cocahNikename;
    private String fitnessCourseCoverimg;
    private int cocahAge;
    private String courseReleaseMoney;

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getFitnessCourseType() {
        return fitnessCourseType;
    }

    public void setFitnessCourseType(int fitnessCourseType) {
        this.fitnessCourseType = fitnessCourseType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreCoachHeadimg() {
        return storeCoachHeadimg;
    }

    public void setStoreCoachHeadimg(String storeCoachHeadimg) {
        this.storeCoachHeadimg = storeCoachHeadimg;
    }

    public String getPlanBegtime() {
        return planBegtime;
    }

    public void setPlanBegtime(String planBegtime) {
        this.planBegtime = planBegtime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public FitnessCourseContextBean getFitnessCourseContext() {
        return fitnessCourseContext;
    }

    public void setFitnessCourseContext(FitnessCourseContextBean fitnessCourseContext) {
        this.fitnessCourseContext = fitnessCourseContext;
    }

    public int getCourseReleaseIsplan() {
        return courseReleaseIsplan;
    }

    public void setCourseReleaseIsplan(int courseReleaseIsplan) {
        this.courseReleaseIsplan = courseReleaseIsplan;
    }

    public StoreCoachIntroduceBean getStoreCoachIntroduce() {
        return storeCoachIntroduce;
    }

    public void setStoreCoachIntroduce(StoreCoachIntroduceBean storeCoachIntroduce) {
        this.storeCoachIntroduce = storeCoachIntroduce;
    }

    public String getCocahId() {
        return cocahId;
    }

    public void setCocahId(String cocahId) {
        this.cocahId = cocahId;
    }

    public String getPlanEndtime() {
        return planEndtime;
    }

    public void setPlanEndtime(String planEndtime) {
        this.planEndtime = planEndtime;
    }

    public String getCourseReleaseFlag() {
        return courseReleaseFlag;
    }

    public void setCourseReleaseFlag(String courseReleaseFlag) {
        this.courseReleaseFlag = courseReleaseFlag;
    }

    public String getStoreCoachSynopsis() {
        return storeCoachSynopsis;
    }

    public void setStoreCoachSynopsis(String storeCoachSynopsis) {
        this.storeCoachSynopsis = storeCoachSynopsis;
    }

    public String getFitnessCourseName() {
        return fitnessCourseName;
    }

    public void setFitnessCourseName(String fitnessCourseName) {
        this.fitnessCourseName = fitnessCourseName;
    }

    public String getStoreCoachTopimg() {
        return storeCoachTopimg;
    }

    public void setStoreCoachTopimg(String storeCoachTopimg) {
        this.storeCoachTopimg = storeCoachTopimg;
    }

    public int getCocahSex() {
        return cocahSex;
    }

    public void setCocahSex(int cocahSex) {
        this.cocahSex = cocahSex;
    }

    public String getCocahNikename() {
        return cocahNikename;
    }

    public void setCocahNikename(String cocahNikename) {
        this.cocahNikename = cocahNikename;
    }

    public String getFitnessCourseCoverimg() {
        return fitnessCourseCoverimg;
    }

    public void setFitnessCourseCoverimg(String fitnessCourseCoverimg) {
        this.fitnessCourseCoverimg = fitnessCourseCoverimg;
    }

    public int getCocahAge() {
        return cocahAge;
    }

    public void setCocahAge(int cocahAge) {
        this.cocahAge = cocahAge;
    }

    public String getCourseReleaseMoney() {
        return courseReleaseMoney;
    }

    public void setCourseReleaseMoney(String courseReleaseMoney) {
        this.courseReleaseMoney = courseReleaseMoney;
    }

    public static class FitnessCourseContextBean {
        /**
         * fitnessCourseintroduce : 哈他瑜伽又名传统瑜伽,瑜伽体系中最实用的一个体系。在哈他( Hatha)这个词中,“哈”(ha)的意思是太阳,“他”(tha)的意思是月亮。它代表男与女,日与夜,阴与阳,冷与热,以及其他任何相辅相成的两个对立面的平衡。练习过程有简单的唱诵,拜日,调息,体式和大休息术
         * trainingEffect : 哈他瑜伽体系从体位姿势开始练习，这一点与传统的“八分支法”不同，因此也被称为“六分支法瑜伽”。哈他瑜伽主要练习如何控制身体和呼吸，更深一层的效果是使身体各机能有序运转，从而使心灵获得宁静，变得祥和
         * SuitablePopulation : 哈他瑜伽最适合初学者，是通过身体的姿势、呼吸和放松的技巧，来达到训练的目的。对神经系统、各种腺体和内脏都大有益处，其目的在于推动有节奏的呼吸和开发身体潜能。哈他瑜伽的动作讲究舒适度和呼吸控制，也适合初级学员练习
         * Reminder : [{"text":"1.运动装备:一身贴身有弹性的舒适瑜伽服+瑜伽裤是在你上课 之前必备物品。在挑选瑜伽服的时候\u2019要尽量选择具有良好吸湿 排汗功能丶包覆性能好的布料,并且穿上之后感觉收身而不紧 身,不会岀现紧勒感最佳。瑜伽垫丶瑜伽砖及瑜伽铺巾也是较为 常用的瑜伽装备。 Handy的瑜伽课程均会为每个学员 免费提供材质健康安全的瑜伽垫及其他所需装备。","type":"text"},{"text":"2.饮食:瑜伽运动要求练习者在练习之前必须空腹,但是空腹 并不等于饥饿,过于饥饿时人体血糖水平大幅度下降,反而不利 于练习。在练习之前的进餐请尽量少吃\u2019并选择低糖食品,辛 辣丶垃圾食品及碳酸充气类的饮料在练习瑜伽之前使绝对禁止食 用的。可选择酸奶丶水果或谷物来补充所需能量","type":"text"},{"text":"3.本地提供更衣室\u2022储物柜\u2022洗手间\u2022淋浴间（储物柜第一个小时免费，第2个小时开始每小时1块钱）","type":"text"},{"text":"4.课程开始前6小时取消，不支持退款。","type":"text"},{"text":"5.内设无限wf网络尽情使用。密码：HANDY20180801","type":"text"},{"text":"重要：成功预约本课程即表示已经阅读并同意（汗的运动注意事项及免责声明）","type":"text"}]
         */

        private String fitnessCourseintroduce;
        private String trainingEffect;
        private String SuitablePopulation;
        private List<ReminderBean> Reminder;

        public String getFitnessCourseintroduce() {
            return fitnessCourseintroduce;
        }

        public void setFitnessCourseintroduce(String fitnessCourseintroduce) {
            this.fitnessCourseintroduce = fitnessCourseintroduce;
        }

        public String getTrainingEffect() {
            return trainingEffect;
        }

        public void setTrainingEffect(String trainingEffect) {
            this.trainingEffect = trainingEffect;
        }

        public String getSuitablePopulation() {
            return SuitablePopulation;
        }

        public void setSuitablePopulation(String SuitablePopulation) {
            this.SuitablePopulation = SuitablePopulation;
        }

        public List<ReminderBean> getReminder() {
            return Reminder;
        }

        public void setReminder(List<ReminderBean> Reminder) {
            this.Reminder = Reminder;
        }

        public static class ReminderBean {
            /**
             * text : 1.运动装备:一身贴身有弹性的舒适瑜伽服+瑜伽裤是在你上课 之前必备物品。在挑选瑜伽服的时候’要尽量选择具有良好吸湿 排汗功能丶包覆性能好的布料,并且穿上之后感觉收身而不紧 身,不会岀现紧勒感最佳。瑜伽垫丶瑜伽砖及瑜伽铺巾也是较为 常用的瑜伽装备。 Handy的瑜伽课程均会为每个学员 免费提供材质健康安全的瑜伽垫及其他所需装备。
             * type : text
             */

            private String text;
            private String type;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

    public static class StoreCoachIntroduceBean {
        private List<GoodCourseBean> goodCourse;
        private List<CertificateListBean> certificateList;

        public List<GoodCourseBean> getGoodCourse() {
            return goodCourse;
        }

        public void setGoodCourse(List<GoodCourseBean> goodCourse) {
            this.goodCourse = goodCourse;
        }

        public List<CertificateListBean> getCertificateList() {
            return certificateList;
        }

        public void setCertificateList(List<CertificateListBean> certificateList) {
            this.certificateList = certificateList;
        }

        public static class GoodCourseBean {
            /**
             * text :
             * type : text
             * goods : 美腹训练
             */

            private String text;
            private String type;
            private String goods;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getGoods() {
                return goods;
            }

            public void setGoods(String goods) {
                this.goods = goods;
            }
        }

        public static class CertificateListBean {
            /**
             * text : 中国健美协会国家级私人教练
             * type : text
             */

            private String text;
            private String type;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
