package com.example.tangboyang1.pojo;

import java.util.ArrayList;
import java.util.List;

public class ImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductsUuidIsNull() {
            addCriterion("products_uuid is null");
            return (Criteria) this;
        }

        public Criteria andProductsUuidIsNotNull() {
            addCriterion("products_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andProductsUuidEqualTo(String value) {
            addCriterion("products_uuid =", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidNotEqualTo(String value) {
            addCriterion("products_uuid <>", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidGreaterThan(String value) {
            addCriterion("products_uuid >", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidGreaterThanOrEqualTo(String value) {
            addCriterion("products_uuid >=", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidLessThan(String value) {
            addCriterion("products_uuid <", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidLessThanOrEqualTo(String value) {
            addCriterion("products_uuid <=", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidLike(String value) {
            addCriterion("products_uuid like", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidNotLike(String value) {
            addCriterion("products_uuid not like", value, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidIn(List<String> values) {
            addCriterion("products_uuid in", values, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidNotIn(List<String> values) {
            addCriterion("products_uuid not in", values, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidBetween(String value1, String value2) {
            addCriterion("products_uuid between", value1, value2, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andProductsUuidNotBetween(String value1, String value2) {
            addCriterion("products_uuid not between", value1, value2, "productsUuid");
            return (Criteria) this;
        }

        public Criteria andImagurlIsNull() {
            addCriterion("imagurl is null");
            return (Criteria) this;
        }

        public Criteria andImagurlIsNotNull() {
            addCriterion("imagurl is not null");
            return (Criteria) this;
        }

        public Criteria andImagurlEqualTo(String value) {
            addCriterion("imagurl =", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlNotEqualTo(String value) {
            addCriterion("imagurl <>", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlGreaterThan(String value) {
            addCriterion("imagurl >", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlGreaterThanOrEqualTo(String value) {
            addCriterion("imagurl >=", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlLessThan(String value) {
            addCriterion("imagurl <", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlLessThanOrEqualTo(String value) {
            addCriterion("imagurl <=", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlLike(String value) {
            addCriterion("imagurl like", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlNotLike(String value) {
            addCriterion("imagurl not like", value, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlIn(List<String> values) {
            addCriterion("imagurl in", values, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlNotIn(List<String> values) {
            addCriterion("imagurl not in", values, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlBetween(String value1, String value2) {
            addCriterion("imagurl between", value1, value2, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlNotBetween(String value1, String value2) {
            addCriterion("imagurl not between", value1, value2, "imagurl");
            return (Criteria) this;
        }

        public Criteria andImagurlNumIsNull() {
            addCriterion("imagurl_num is null");
            return (Criteria) this;
        }

        public Criteria andImagurlNumIsNotNull() {
            addCriterion("imagurl_num is not null");
            return (Criteria) this;
        }

        public Criteria andImagurlNumEqualTo(Integer value) {
            addCriterion("imagurl_num =", value, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumNotEqualTo(Integer value) {
            addCriterion("imagurl_num <>", value, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumGreaterThan(Integer value) {
            addCriterion("imagurl_num >", value, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("imagurl_num >=", value, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumLessThan(Integer value) {
            addCriterion("imagurl_num <", value, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumLessThanOrEqualTo(Integer value) {
            addCriterion("imagurl_num <=", value, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumIn(List<Integer> values) {
            addCriterion("imagurl_num in", values, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumNotIn(List<Integer> values) {
            addCriterion("imagurl_num not in", values, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumBetween(Integer value1, Integer value2) {
            addCriterion("imagurl_num between", value1, value2, "imagurlNum");
            return (Criteria) this;
        }

        public Criteria andImagurlNumNotBetween(Integer value1, Integer value2) {
            addCriterion("imagurl_num not between", value1, value2, "imagurlNum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}