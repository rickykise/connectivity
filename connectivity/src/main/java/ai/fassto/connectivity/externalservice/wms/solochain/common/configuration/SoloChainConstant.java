package ai.fassto.connectivity.externalservice.wms.solochain.common.configuration;

public class SoloChainConstant {
    public static final String FASSTO_APPLICATION = "FasstoApplication";
    public static final String SOLOCHAIN_APPLICATION = "SolochainApplication";
    public static final String RESPONSE_SUCCESS = "Success";
    public static final String RESPONSE_ERROR = "Error";

    public static final String SERVICEABLE = "SERVICEABLE";
    public static final String INBOUND = "Inbound";
    public static final String OUTBOUND = "Outbound";
    public static final String PURCHASE_ORDER = "Purchase Order";
    public static final String SITE = "Site";
    public static final String OVERRIDE = "Override";
    public static final String PARENT = "Parent";
    public static final String SHIPMENT_REQUEST = "ShipmentRequest";
    public static final String BY_REFERENCE_AND_REFERENCE_NO = "ByReferenceAndReferenceNo";

    public static final String BY_MATERIAL_MASTER_AND_SITE = "ByMaterialMasterAndSite";
    public static final String MATERIAL_MASTER = "MaterialMaster";
    public static final String PROMPT = "Prompt";
    public static final String NONE = "None";
    public static final String HARD = "Hard";
    public static final String DISABLED = "Disabled";
    public static final String AUTOMATIC = "Automatic";
    public static final String FALSE = "FALSE";
    public static final String STRICT = "Strict";
    public static final String ACTIVE = "ACTIVE";
    public static final String CANCELLED = "CANCELLED";
    public static final String RECEIVING = "Receiving";
    public static final String MANUAL = "Manual";
    public static final String INTERNAL = "Internal";
    public static final String INTERNAL_REQUEST = "InternalRequest";
    public static final String TRANSFER = "Transfer";
    public static final String MMLOT_NUMBER = "MMLotNumber";
    public static final String LOT_NUMBER = "LotNumber";
    public static final String DATE_VENDOR_PART_NO = "DateVendorPartNo";
    public static final String BY_MATERIAL_MASTER_VALUE = "ByMaterialMasterValue";
    public static final String CATEGORY1 = "Category1";
    public static final String CATEGORY2 = "Category2";
    public static final String CATEGORY3 = "Category3";
    public static final String SOLH_ = "SOLH";
    public static final String AMAZON_S3_BUCKET = "AmazonS3Bucket";
    public static final String MATERIAL_CONDITION = "MaterialCondition";
    public static final String BY_NAME = "ByName";

    /**
     * DOMAIN MEMBER
     */
    public static final String DOMAIN_CUSTOMER = "Customer";
    public static final String DOMAIN_VENDOR = "Vendor";
    public static final String DOMAIN_WORK_ORDER = "WorkOrder";
    public static final String DOMAIN_ITEM = "Item";
    public static final String DOMAIN_PURCHASE_ORDER = "PurchaseOrder";
    public static final String DOMAIN_SALES_ORDER = "SalesOrder";
    public static final String DOMAIN_PARCEL = "Parcel";
    public static final String SHIPPABLE = "Shippable";
    public static final String STOCK = "Stock";




    public static boolean isSuccessResponse(String result) {
        return RESPONSE_SUCCESS.equalsIgnoreCase(result);
    }
}
