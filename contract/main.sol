// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract DrugTrackingSystem {
    address public owner;
    address public verifyingAuthority;
    uint256 public nextDrugId;
    uint256 public nextOrderId;

    enum DrugStatus {
        Created,
        Approved,
        Dispatched,
        Delivered
    }
    enum OrderStatus {
        Placed,
        Approved
    }

    struct Drug {
        uint256 id;
        string details;
        address manufacturer;
        DrugStatus status;
        address approvedBy;
        address requestedBy;
        uint256 manufacturingDate;
        uint256 expirationDate;
        string batchNumber;
    }

    struct Order {
        uint256 id;
        uint256 drugId;
        address patient;
        uint256 quantity;
        OrderStatus status;
    }

    mapping(uint256 => Drug) public drugs;
    mapping(uint256 => Order) public orders;
    mapping(address => bool) public approvedAddresses;

    event DrugCreated(
        uint256 indexed drugId,
        string details,
        address manufacturer
    );
    event DrugApproved(uint256 indexed drugId, address indexed approvedBy);
    event DrugDispatched(uint256 indexed drugId, address indexed requestedBy);
    event DrugDelivered(uint256 indexed drugId, address indexed requestedBy);
    event PatientDrugRequested(uint256 indexed drugId, address indexed patient);
    event OrderPlaced(
        uint256 indexed orderId,
        uint256 indexed drugId,
        address indexed patient,
        uint256 quantity
    );
    event OrderApproved(
        uint256 indexed orderId,
        uint256 indexed drugId,
        address indexed patient,
        uint256 quantity
    );

    modifier onlyOwner() {
        require(msg.sender == owner, "Only owner can call this function");
        _;
    }

    modifier onlyVerifyingAuthority() {
        require(
            msg.sender == verifyingAuthority,
            "Only verifying authority can call this function"
        );
        _;
    }

    modifier onlyApprovedAddress() {
        require(approvedAddresses[msg.sender], "Address is not approved");
        _;
    }

    modifier onlyManufacturer(uint256 drugId) {
        require(
            drugs[drugId].manufacturer == msg.sender,
            "Only manufacturer can call this function"
        );
        _;
    }

    constructor() {
        owner = msg.sender;
    }

    function setVerifyingAuthority(
        address _verifyingAuthority
    ) external onlyOwner {
        verifyingAuthority = _verifyingAuthority;
    }

    function approveAddress(
        address _approvedAddress
    ) external onlyVerifyingAuthority {
        approvedAddresses[_approvedAddress] = true;
    }

    function createDrug(
        string memory details,
        uint256 manufacturingDate,
        uint256 expirationDate,
        string memory batchNumber
    ) external onlyManufacturer(nextDrugId) {
        require(bytes(details).length > 0, "Drug details cannot be empty");
        require(
            manufacturingDate < expirationDate,
            "Invalid manufacturing or expiration date"
        );

        uint256 drugId = nextDrugId++;
        drugs[drugId] = Drug(
            drugId,
            details,
            msg.sender,
            DrugStatus.Created,
            address(0),
            address(0),
            manufacturingDate,
            expirationDate,
            batchNumber
        );
        emit DrugCreated(drugId, details, msg.sender);
    }

    function approveDrug(uint256 drugId) external onlyVerifyingAuthority {
        Drug storage drug = drugs[drugId];
        require(
            drug.status == DrugStatus.Created,
            "Drug is not in the created state"
        );
        drug.status = DrugStatus.Approved;
        drug.approvedBy = msg.sender;
        emit DrugApproved(drugId, msg.sender);
    }

    function placeOrder(
        uint256 drugId,
        uint256 quantity
    ) external onlyApprovedAddress {
        Drug storage drug = drugs[drugId];
        require(drug.status == DrugStatus.Approved, "Drug is not approved yet");

        uint256 orderId = nextOrderId++;
        orders[orderId] = Order(
            orderId,
            drugId,
            msg.sender,
            quantity,
            OrderStatus.Placed
        );
        emit OrderPlaced(orderId, drugId, msg.sender, quantity);
    }

    function approveOrder(uint256 orderId) external onlyVerifyingAuthority {
        Order storage order = orders[orderId];
        require(
            order.status == OrderStatus.Placed,
            "Order is not in the placed state"
        );

        order.status = OrderStatus.Approved;
        emit OrderApproved(
            orderId,
            order.drugId,
            order.patient,
            order.quantity
        );
    }
}
