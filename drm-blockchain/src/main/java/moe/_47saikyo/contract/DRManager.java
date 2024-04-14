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
    public static final String BINARY = "6080604052348015600f57600080fd5b50610a878061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c80638a28fc53116100715780638a28fc531461015e578063a5b06b3b14610173578063b93a9a461461017b578063ccaf08041461018e578063e79f0e96146101a1578063ff281d72146101b457600080fd5b806314773b4f146100b957806321875110146100e2578063299a4f6a1461010557806347d0436814610130578063490c8ce2146101435780635ce6f0f01461014b575b600080fd5b6100cc6100c736600461076e565b6101c7565b6040516100d99190610792565b60405180910390f35b6100f56100f036600461076e565b61023d565b60405190151581526020016100d9565b6101186101133660046108a4565b61024f565b6040516001600160a01b0390911681526020016100d9565b61011861013e3660046108a4565b610280565b6100cc610292565b6100f56101593660046108d9565b6102a2565b61017161016c366004610961565b6102e0565b005b6100cc61031d565b6100f5610189366004610961565b610328565b61017161019c36600461076e565b61068a565b6100cc6101af36600461076e565b610697565b6101186101c23660046108a4565b610709565b6001600160a01b03811660009081526001602090815260409182902080548351818402810184019094528084526060939283018282801561023157602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610213575b50505050509050919050565b60006102493383610328565b92915050565b600060028260405161026191906109be565b908152604051908190036020019020546001600160a01b031692915050565b600060038260405161026191906109be565b606061029d33610697565b905090565b60006102af60028561071b565b1580156102c457506102c260038461071b565b155b80156102d857506102d660048361071b565b155b949350505050565b6001600160a01b03918216600090815260016020818152604083208054928301815583529091200180546001600160a01b03191691909216179055565b606061029d336101c7565b600061045f826001600160a01b031663ff3c1a8f6040518163ffffffff1660e01b8152600401600060405180830381865afa15801561036b573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261039391908101906109da565b836001600160a01b0316639cf834cb6040518163ffffffff1660e01b8152600401600060405180830381865afa1580156103d1573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526103f991908101906109da565b846001600160a01b0316638493f71f6040518163ffffffff1660e01b8152600401600060405180830381865afa158015610437573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261015991908101906109da565b61046b57506000610249565b816002836001600160a01b031663ff3c1a8f6040518163ffffffff1660e01b8152600401600060405180830381865afa1580156104ac573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526104d491908101906109da565b6040516104e191906109be565b908152602001604051809103902060006101000a8154816001600160a01b0302191690836001600160a01b03160217905550816003836001600160a01b0316639cf834cb6040518163ffffffff1660e01b8152600401600060405180830381865afa158015610554573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261057c91908101906109da565b60405161058991906109be565b9081526040805191829003602001822080546001600160a01b039485166001600160a01b0319909116179055638493f71f60e01b825251849260049290841691638493f71f9184810191600091819003860181865afa1580156105f0573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405261061891908101906109da565b60405161062591906109be565b908152604080516020928190038301902080546001600160a01b03199081166001600160a01b03958616179091558684166000908152808452918220805460018181018355918452939092209092018054909216928516929092179055905092915050565b61069433826102e0565b50565b6001600160a01b03811660009081526020818152604091829020805483518184028101840190945280845260609392830182828015610231576020028201919060005260206000209081546001600160a01b031681526001909101906020018083116102135750505050509050919050565b600060048260405161026191906109be565b6000806001600160a01b0316838360405161073691906109be565b908152604051908190036020019020546001600160a01b03161415905092915050565b6001600160a01b038116811461069457600080fd5b60006020828403121561078057600080fd5b813561078b81610759565b9392505050565b6020808252825182820181905260009190848201906040850190845b818110156107d35783516001600160a01b0316835292840192918401916001016107ae565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561081e5761081e6107df565b604052919050565b600067ffffffffffffffff821115610840576108406107df565b50601f01601f191660200190565b600082601f83011261085f57600080fd5b813561087261086d82610826565b6107f5565b81815284602083860101111561088757600080fd5b816020850160208301376000918101602001919091529392505050565b6000602082840312156108b657600080fd5b813567ffffffffffffffff8111156108cd57600080fd5b6102d88482850161084e565b6000806000606084860312156108ee57600080fd5b833567ffffffffffffffff8082111561090657600080fd5b6109128783880161084e565b9450602086013591508082111561092857600080fd5b6109348783880161084e565b9350604086013591508082111561094a57600080fd5b506109578682870161084e565b9150509250925092565b6000806040838503121561097457600080fd5b823561097f81610759565b9150602083013561098f81610759565b809150509250929050565b60005b838110156109b557818101518382015260200161099d565b50506000910152565b600082516109d081846020870161099a565b9190910192915050565b6000602082840312156109ec57600080fd5b815167ffffffffffffffff811115610a0357600080fd5b8201601f81018413610a1457600080fd5b8051610a2261086d82610826565b818152856020838501011115610a3757600080fd5b610a4882602083016020860161099a565b9594505050505056fea264697066735822122035b08be7860993ec250daeec4bcf1e8706a37491b1552edee94d2eba6ae8d17364736f6c63430008190033";

    public static final String FUNC_addLicense = "addLicense";

    public static final String FUNC_addRight = "addRight";

    public static final String FUNC_CANINSERTRIGHT = "canInsertRight";

    public static final String FUNC_getLicenses = "getLicenses";

    public static final String FUNC_GETRIGHTBYFILEHASH = "getRightByFileHash";

    public static final String FUNC_GETRIGHTBYREGISTRATIONNUMBER = "getRightByRegistrationNumber";

    public static final String FUNC_GETRIGHTBYTITLE = "getRightByTitle";

    public static final String FUNC_getRights = "getRights";

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

    public RemoteFunctionCall<Boolean> canInsertRight(String title, String registrationNumber, String fileHash) {
        final Function function = new Function(FUNC_CANINSERTRIGHT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(title), 
                new org.web3j.abi.datatypes.Utf8String(registrationNumber), 
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

    public RemoteFunctionCall<String> getRightByTitle(String title) {
        final Function function = new Function(FUNC_GETRIGHTBYTITLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(title)), 
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
