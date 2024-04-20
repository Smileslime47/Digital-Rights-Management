package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.2.
 */
@SuppressWarnings("rawtypes")
public class DRManager extends Contract {
    public static final String BINARY = "6080604052348015600f57600080fd5b506114f18061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c80638a28fc53116100715780638a28fc531461015e578063a5b06b3b14610173578063b93a9a461461017b578063ccaf08041461018e578063e79f0e96146101a1578063ff281d72146101b457600080fd5b806314773b4f146100b957806321875110146100e25780632c109ae61461010557806347d0436814610118578063490c8ce2146101435780635bab3ba11461014b575b600080fd5b6100cc6100c7366004611020565b6101c7565b6040516100d9919061103d565b60405180910390f35b6100f56100f0366004611020565b61023d565b60405190151581526020016100d9565b6100f561011336600461114f565b61024f565b61012b6101263660046111b3565b610278565b6040516001600160a01b0390911681526020016100d9565b6100cc6102a9565b6100cc6101593660046111b3565b6102b9565b61017161016c3660046111f0565b6104e7565b005b6100cc610524565b6100f56101893660046111f0565b61052f565b61017161019c366004611020565b61080a565b6100cc6101af366004611020565b610817565b61012b6101c23660046111b3565b610889565b6001600160a01b03811660009081526001602090815260409182902080548351818402810184019094528084526060939283018282801561023157602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610213575b50505050509050919050565b6000610249338361052f565b92915050565b600061025c60028461089b565b158015610271575061026f60038361089b565b155b9392505050565b600060028260405161028a919061124d565b908152604051908190036020019020546001600160a01b031692915050565b60606102b433610817565b905090565b60606102c66004836108d9565b156103905760006102d86004846109e0565b5167ffffffffffffffff8111156102f1576102f161108a565b60405190808252806020026020018201604052801561031a578160200160208202803683370190505b50905060005b61032b6004856109e0565b518110156103895761033e6004856109e0565b818151811061034f5761034f611269565b602002602001015182828151811061036957610369611269565b6001600160a01b0390921660209283029190910190910152600101610320565b5092915050565b6000805b6005548110156103e0576103b36103ac600483610a89565b5185610bc7565b600019146103d8576103c6600482610a89565b60200151516103d59083611295565b91505b600101610394565b5060008167ffffffffffffffff8111156103fc576103fc61108a565b604051908082528060200260200182016040528015610425578160200160208202803683370190505b5090506000805b6005548110156104dd5761044b610444600483610a89565b5187610bc7565b600019146104d55760005b610461600483610a89565b60200151518110156104d357610478600483610a89565b60200151818151811061048d5761048d611269565b60200260200101518484806104a1906112a8565b9550815181106104b3576104b3611269565b6001600160a01b0390921660209283029190910190910152600101610456565b505b60010161042c565b5090949350505050565b6001600160a01b03918216600090815260016020818152604083208054928301815583529091200180546001600160a01b03191691909216179055565b60606102b4336101c7565b6000610600826001600160a01b0316639cf834cb6040518163ffffffff1660e01b8152600401600060405180830381865afa158015610572573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261059a91908101906112c1565b836001600160a01b0316638493f71f6040518163ffffffff1660e01b8152600401600060405180830381865afa1580156105d8573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261011391908101906112c1565b61060c57506000610249565b816002836001600160a01b0316639cf834cb6040518163ffffffff1660e01b8152600401600060405180830381865afa15801561064d573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261067591908101906112c1565b604051610682919061124d565b908152602001604051809103902060006101000a8154816001600160a01b0302191690836001600160a01b03160217905550816003836001600160a01b0316638493f71f6040518163ffffffff1660e01b8152600401600060405180830381865afa1580156106f5573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261071d91908101906112c1565b60405161072a919061124d565b9081526040805191829003602001822080546001600160a01b039485166001600160a01b0319909116179055600162c3e57160e01b03198252516107c4926004929086169163ff3c1a8f9184810191600091819003860181865afa158015610796573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526107be91908101906112c1565b84610c99565b506001600160a01b038281166000908152602081815260408220805460018082018355918452919092200180546001600160a01b03191692841692909217909155610249565b61081433826104e7565b50565b6001600160a01b03811660009081526020818152604091829020805483518184028101840190945280845260609392830182828015610231576020028201919060005260206000209081546001600160a01b031681526001909101906020018083116102135750505050509050919050565b600060038260405161028a919061124d565b6000806001600160a01b031683836040516108b6919061124d565b908152604051908190036020019020546001600160a01b03161415905092915050565b600082600001826040516108ed919061124d565b9081526020016040518091039020546000036109d8576000610910846001015490565b1180156109d157506109d18360010160008154811061093157610931611269565b9060005260206000209060020201600001805461094d90611338565b80601f016020809104026020016040519081016040528092919081815260200182805461097990611338565b80156109c65780601f1061099b576101008083540402835291602001916109c6565b820191906000526020600020905b8154815290600101906020018083116109a957829003601f168201915b505050505083610db3565b9050610249565b506001610249565b60608260010183600001836040516109f8919061124d565b90815260200160405180910390205481548110610a1757610a17611269565b9060005260206000209060020201600101805480602002602001604051908101604052809291908181526020018280548015610a7c57602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610a5e575b5050505050905092915050565b6040805180820190915260608082526020820152826001018281548110610ab257610ab2611269565b9060005260206000209060020201604051806040016040529081600082018054610adb90611338565b80601f0160208091040260200160405190810160405280929190818152602001828054610b0790611338565b8015610b545780601f10610b2957610100808354040283529160200191610b54565b820191906000526020600020905b815481529060010190602001808311610b3757829003601f168201915b5050505050815260200160018201805480602002602001604051908101604052809291908181526020018280548015610bb657602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610b98575b505050505081525050905092915050565b815181516000919082610bd985610e47565b90506000805b8482128015610bed57508381125b15610c6d57806000191480610c275750610c078782610f66565b6001600160f81b031916610c1b8984610f66565b6001600160f81b031916145b15610c4c5781610c3681611372565b9250508080610c4490611372565b915050610bdf565b828181518110610c5e57610c5e611269565b60200260200101519050610bdf565b838103610c8a57610c7e818361138a565b95505050505050610249565b60001995505050505050610249565b60008360000183604051610cad919061124d565b9081526020016040518091039020549050610cc884846108d9565b610d585760018481018054604080518082018252878152815160008082526020808301909452838301919091529483018455928452909220815160028402909101908190610d1690826113fb565b506020828101518051610d2f9260018501920190610f91565b505050808560000185604051610d45919061124d565b9081526040519081900360200190205590505b836001018181548110610d6d57610d6d611269565b60009182526020808320600160029093020182018054928301815583529091200180546001600160a01b039093166001600160a01b031990931692909217909155505050565b805182516000918491849114610dce57600092505050610249565b60005b8251811015610e3b57818181518110610dec57610dec611269565b602001015160f81c60f81b6001600160f81b031916838281518110610e1357610e13611269565b01602001516001600160f81b03191614610e335760009350505050610249565b600101610dd1565b50600195945050505050565b805160609060008167ffffffffffffffff811115610e6757610e6761108a565b604051908082528060200260200182016040528015610e90578160200160208202803683370190505b50905060001981600081518110610ea957610ea9611269565b602090810291909101015260001960005b610ec560018561138a565b8112156104dd57816000191480610f015750610ee18683610f66565b6001600160f81b031916610ef58783610f66565b6001600160f81b031916145b15610f455781610f1081611372565b9250508080610f1e90611372565b91505081838281518110610f3457610f34611269565b602002602001018181525050610eba565b828281518110610f5757610f57611269565b60200260200101519150610eba565b6000828281518110610f7a57610f7a611269565b01602001516001600160f81b031916905092915050565b828054828255906000526020600020908101928215610fe6579160200282015b82811115610fe657825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190610fb1565b50610ff2929150610ff6565b5090565b5b80821115610ff25760008155600101610ff7565b6001600160a01b038116811461081457600080fd5b60006020828403121561103257600080fd5b81356102718161100b565b6020808252825182820181905260009190848201906040850190845b8181101561107e5783516001600160a01b031683529284019291840191600101611059565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156110c9576110c961108a565b604052919050565b600067ffffffffffffffff8211156110eb576110eb61108a565b50601f01601f191660200190565b600082601f83011261110a57600080fd5b813561111d611118826110d1565b6110a0565b81815284602083860101111561113257600080fd5b816020850160208301376000918101602001919091529392505050565b6000806040838503121561116257600080fd5b823567ffffffffffffffff8082111561117a57600080fd5b611186868387016110f9565b9350602085013591508082111561119c57600080fd5b506111a9858286016110f9565b9150509250929050565b6000602082840312156111c557600080fd5b813567ffffffffffffffff8111156111dc57600080fd5b6111e8848285016110f9565b949350505050565b6000806040838503121561120357600080fd5b823561120e8161100b565b9150602083013561121e8161100b565b809150509250929050565b60005b8381101561124457818101518382015260200161122c565b50506000910152565b6000825161125f818460208701611229565b9190910192915050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b808201808211156102495761024961127f565b6000600182016112ba576112ba61127f565b5060010190565b6000602082840312156112d357600080fd5b815167ffffffffffffffff8111156112ea57600080fd5b8201601f810184136112fb57600080fd5b8051611309611118826110d1565b81815285602083850101111561131e57600080fd5b61132f826020830160208601611229565b95945050505050565b600181811c9082168061134c57607f821691505b60208210810361136c57634e487b7160e01b600052602260045260246000fd5b50919050565b60006001600160ff1b0182016112ba576112ba61127f565b81810360008312801583831316838312821617156103895761038961127f565b601f8211156113f6576000816000526020600020601f850160051c810160208610156113d35750805b601f850160051c820191505b818110156113f2578281556001016113df565b5050505b505050565b815167ffffffffffffffff8111156114155761141561108a565b611429816114238454611338565b846113aa565b602080601f83116001811461145e57600084156114465750858301515b600019600386901b1c1916600185901b1785556113f2565b600085815260208120601f198616915b8281101561148d5788860151825594840194600190910190840161146e565b50858210156114ab5787850151600019600388901b60f8161c191681555b5050505050600190811b0190555056fea264697066735822122011e28d90aac0bafcb2a87708caf8624e7d95a5550546edb5afdd5531818f142364736f6c63430008190033";

    public static final String FUNC_addLicense = "addLicense";

    public static final String FUNC_addRight = "addRight";

    public static final String FUNC_CANINSERTRIGHT = "canInsertRight";

    public static final String FUNC_getLicenses = "getLicenses";

    public static final String FUNC_GETRIGHTBYFILEHASH = "getRightByFileHash";

    public static final String FUNC_GETRIGHTBYREGISTRATIONNUMBER = "getRightByRegistrationNumber";

    public static final String FUNC_getRights = "getRights";

    public static final String FUNC_SEARCHBYTITLE = "searchByTitle";

    @Deprecated
    protected DRManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DRManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DRManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DRManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String owner, String license) {
        final Function function = new Function(
                FUNC_addLicense, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String license) {
        final Function function = new Function(
                FUNC_addLicense, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRight(String right) {
        final Function function = new Function(
                FUNC_addRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, right)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addRight(String owner, String right) {
        final Function function = new Function(
                FUNC_addRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, right)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> canInsertRight(String registrationNumber, String fileHash) {
        final Function function = new Function(FUNC_CANINSERTRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(registrationNumber), 
                new org.web3j.abi.datatypes.Utf8String(fileHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<List> getLicenses(String owner) {
        final Function function = new Function(FUNC_getLicenses, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getLicenses() {
        final Function function = new Function(FUNC_getLicenses, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getRightByFileHash(String fileHash) {
        final Function function = new Function(FUNC_GETRIGHTBYFILEHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(fileHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getRightByRegistrationNumber(String registrationNumber) {
        final Function function = new Function(FUNC_GETRIGHTBYREGISTRATIONNUMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(registrationNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getRights() {
        final Function function = new Function(FUNC_getRights, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getRights(String owner) {
        final Function function = new Function(FUNC_getRights, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> searchByTitle(String title) {
        final Function function = new Function(FUNC_SEARCHBYTITLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(title)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static DRManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DRManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DRManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DRManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DRManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DRManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DRManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DRManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DRManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DRManager.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DRManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DRManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DRManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DRManager.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DRManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DRManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
