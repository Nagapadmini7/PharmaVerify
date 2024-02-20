const { ethers } = require("hardhat");

async function main() {
    const DrugTrackingSystem = await ethers.getContractFactory("DrugTrackingSystem");
    const drugTrackingSystem = await DrugTrackingSystem.deploy();

    console.log("DrugTrackingSystem deployed to:", drugTrackingSystem.address);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });
