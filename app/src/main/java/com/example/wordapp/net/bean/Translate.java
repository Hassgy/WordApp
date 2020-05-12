package com.example.wordapp.net.bean;

import androidx.annotation.NonNull;

import java.util.List;

public class Translate {

    /**
     * sentences : [{"trans":"学校","orig":"school","backend":1}]
     * src : en
     * confidence : 0.92607003
     * spell : {}
     * ld_result : {"srclangs":["en"],"srclangs_confidences":[0.92607003],"extended_srclangs":["en"]}
     */

    private String src;
    private double confidence;
    private SpellBean spell;
    private LdResultBean ld_result;
    private List<SentencesBean> sentences;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public SpellBean getSpell() {
        return spell;
    }

    public void setSpell(SpellBean spell) {
        this.spell = spell;
    }

    public LdResultBean getLd_result() {
        return ld_result;
    }

    public void setLd_result(LdResultBean ld_result) {
        this.ld_result = ld_result;
    }

    public List<SentencesBean> getSentences() {
        return sentences;
    }

    public void setSentences(List<SentencesBean> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "Translate{" +
                "src='" + src + '\'' +
                ", confidence=" + confidence +
                ", spell=" + spell +
                ", ld_result=" + ld_result +
                ", sentences=" + sentences +
                '}';
    }

    public static class SpellBean {
    }

    public static class LdResultBean {
        private List<String> srclangs;
        private List<Double> srclangs_confidences;
        private List<String> extended_srclangs;

        public List<String> getSrclangs() {
            return srclangs;
        }

        public void setSrclangs(List<String> srclangs) {
            this.srclangs = srclangs;
        }

        public List<Double> getSrclangs_confidences() {
            return srclangs_confidences;
        }

        public void setSrclangs_confidences(List<Double> srclangs_confidences) {
            this.srclangs_confidences = srclangs_confidences;
        }

        public List<String> getExtended_srclangs() {
            return extended_srclangs;
        }

        public void setExtended_srclangs(List<String> extended_srclangs) {
            this.extended_srclangs = extended_srclangs;
        }
    }

    public static class SentencesBean {
        /**
         * trans : 学校
         * orig : school
         * backend : 1
         */

        private String trans;
        private String orig;
        private int backend;

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }

        public String getOrig() {
            return orig;
        }

        public void setOrig(String orig) {
            this.orig = orig;
        }

        public int getBackend() {
            return backend;
        }

        public void setBackend(int backend) {
            this.backend = backend;
        }
    }
}
