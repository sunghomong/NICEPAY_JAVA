package com.nicepay.api.common.model;

import com.nicepay.api.common.exception.NicePayCode;
import lombok.Getter;

/**
 * NicePayBankCode
 * Desc : comment
 *
 * @author : Sung Ho Cho
 * @version : 1.0
 * Date : 2024-12-27
 */
@Getter
public enum NicePayBankCode {

    KOREAN_BANK("001", "한국은행"),
    INDUSTRY_BANK("002", "산업은행"),
    IBK_VIRTUAL_ACCOUNT("003", "기업은행"), // 가상계좌 채번가능
    KB_VIRTUAL_ACCOUNT("004", "국민은행"), // 가상계좌 채번가능
    WOO_BANK("005", "외환은행"),
    FISHERY_COOPERATIVE_BANK("007", "수협은행"),
    EXPORT_IMPORT_BANK("008", "수출입은행"),
    NH_VIRTUAL_ACCOUNT("011", "농협은행"), // 가상계좌 채번가능
    NH_MEMBER_COOPERATIVE("012", "농협회원조합"),
    WOORI_VIRTUAL_ACCOUNT("020", "우리은행"), // 가상계좌 채번가능
    SC_VIRTUAL_ACCOUNT("023", "SC제일은행"), // 가상계좌 채번가능
    SEOUL_BANK("026", "서울은행"),
    KOREA_CITIBANK("027", "한국씨티은행"),
    IM_BANK_DC("031", "iM뱅크(대구)"), // 가상계좌 채번가능
    BUSAN_VIRTUAL_ACCOUNT("032", "부산은행"), // 가상계좌 채번가능
    GWANGJU_VIRTUAL_ACCOUNT("034", "광주은행"), // 가상계좌 채번가능
    JEJU_BANK("035", "제주은행"),
    JEONBUK_BANK("037", "전북은행"),
    GYEONGNAM_BANK("039", "경남은행"),
    SAEMAEUL_CREDIT_UNION("045", "새마을금고연합회"),
    SHINHANK_BANK("048", "신협중앙회"),
    MUTUAL_SAVINGS_BANK("050", "상호저축은행"),
    FOREIGN_BANK_1("051", "기타 외국계은행"),
    MORGAN_STANLEY_BANK("052", "모건스탠리은행"),
    HSBC_BANK("054", "HSBC은행"),
    DEUTSCHE_BANK("055", "도이치은행"),
    RBS_BANK("056", "알비에스피엘씨은행"),
    JP_MORGAN_CHASE("057", "제이피모간체이스은행"),
    MIZUHO_BANK("058", "미즈호코퍼레이트은행"),
    MITSUBISHI_BANK("059", "미쓰비시도쿄UFJ은행"),
    BOA("060", "BOA"),
    BNP_PARIBAS_BANK("061", "비엔피파리바은행"),
    ICBC_BANK("062", "중국공상은행"),
    BANK_OF_CHINA("063", "중국은행"),
    FOREST_FARMERS_COOPERATIVE("064", "산림조합"),
    DAEHWA_BANK("065", "대화은행"),
    POST_VIRTUAL_ACCOUNT("071", "우체국"), // 가상계좌 채번가능
    CREDIT_GUARANTEE_FUND("076", "신용보증기금"),
    TECH_GUARANTEE_FUND("077", "기술신용보증기금"),
    HANA_VIRTUAL_ACCOUNT("081", "하나은행"), // 가상계좌 채번가능
    SHINHAN_VIRTUAL_ACCOUNT("088", "신한은행"), // 가상계좌 채번가능
    K_BANK_VIRTUAL_ACCOUNT("089", "케이뱅크"), // 가상계좌 채번가능
    KAKAO_BANK("090", "카카오뱅크"),
    TOSS_BANK("092", "토스뱅크"),
    KOREA_HOUSING_FINANCE_CORPORATION("093", "한국주택금융공사"),
    SEOUL_GUARANTEE_INSURANCE("094", "서울보증보험"),
    POLICE_AGENCY("095", "경찰청"),
    PAYMENT_SETTLEMENT_CENTER("099", "금융결제원"),
    DONGYANG_SECURITIES("209", "동양종합금융증권"),
    HYUNDAI_SECURITIES("218", "현대증권"),
    MIRAESSET_SECURITIES("230", "미래에셋증권"),
    DAEWOO_SECURITIES("238", "대우증권"),
    SAMSUNG_SECURITIES("240", "삼성증권"),
    KOREA_INVESTMENT_SECURITIES("243", "한국투자증권"),
    NH_INVESTMENT_SECURITIES("247", "NH 투자증권"),
    KYOBO_SECURITIES("261", "교보증권"),
    HI_INVESTMENT_SECURITIES("262", "하이투자증권"),
    HMC_INVESTMENT_SECURITIES("263","에이치엠씨투자증권"),
    KIWOOM_SECURITIES("264","키움증권"),
    E_TRADE_SECURITIES("265", "이트레이드증권"),
    SK_SECURITIES("266", "SK증권"),
    DAISHIN_SECURITIES("267", "대신증권"),
    SOLOMON_SECURITIES("268", "솔로몬투자증권"),
    HANWHA_SECURITIES("269", "한화증권"),
    HANADAETU_SECURITIES("270", "하나대투증권"),
    TOSS_SECURITIES("271", "토스증권"),
    SHINHAN_FINANCIAL_INVESTMENT("278", "신한금융투자"),
    DONGBU_SECURITIES("279", "동부증권"),
    YUJIN_INVESTMENT_SECURITIES("280", "유진투자증권"),
    MERITZ_SECURITIES("287", "메리츠증권"),
    NH_INVESTMENT_SECURITIES_K_LANG("289", "엔에이치투자증권"),
    BUKOOK_SECURITIES("290", "부국증권"),
    SHINYOUNG_SECURITIES("291", "신영증권"),
    LIG_INVESTMENT_SECURITIES("292", "엘아이지투자증권"),
    CODE_ERROR("999","알 수 없는 은행 코드");

    private String bankCode;
    private String bankName;

    NicePayBankCode(String bankCode, String bankName) {
        this.bankCode = bankCode;
        this.bankName = bankName;
    }


    public static NicePayBankCode fromCode(String code) {

        for (NicePayBankCode constant : values()) {
            if (constant.bankCode.equals(code)) {
                return constant;
            }
        }

        /* 해당하는 코드가 없을 경우 */
        return NicePayBankCode.CODE_ERROR;
    }
}
