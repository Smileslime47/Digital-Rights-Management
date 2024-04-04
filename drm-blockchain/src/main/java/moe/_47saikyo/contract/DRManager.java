package moe._47saikyo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
    public static final String BINARY = "6080604052348015600f57600080fd5b506108228061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80632187511014610067578063490c8ce21461007c5780636c775a8f1461009a578063b93a9a46146100c5578063e79f0e96146100d8578063ee82ff8f146100eb575b600080fd5b61007a6100753660046103df565b6100fe565b005b61008461010b565b60405161009191906103fc565b60405180910390f35b6100ad6100a836600461050e565b61011b565b6040516001600160a01b039091168152602001610091565b61007a6100d336600461055e565b610144565b6100846100e63660046103df565b6101db565b6100ad6100f9366004610597565b6102a6565b6101083382610144565b50565b6060610116336101db565b905090565b6001600160a01b038216600090815260208190526040812061013d90836102b8565b9392505050565b6101d7600080846001600160a01b03166001600160a01b03168152602001908152602001600020826001600160a01b031663ff3c1a8f6040518163ffffffff1660e01b8152600401600060405180830381865afa1580156101a9573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526101d191908101906105f8565b83610313565b5050565b6001600160a01b0381166000908152602081905260408120600101546060918167ffffffffffffffff81111561021357610213610449565b60405190808252806020026020018201604052801561023c578160200160208202803683370190505b50905060005b8281101561029e576001600160a01b038516600090815260208190526040902061026c90826103b3565b82828151811061027e5761027e61066f565b6001600160a01b0390921660209283029190910190910152600101610242565b509392505050565b60006102b2338361011b565b92915050565b60008260010183600001836040516102d09190610685565b908152602001604051809103902054815481106102ef576102ef61066f565b60009182526020909120600290910201600101546001600160a01b03169392505050565b60018084018054604080518082019091528581526001600160a01b0385166020808301919091529382018355600092835292909120825191929160028402909101908190610361908261072c565b5060209190910151600190910180546001600160a01b0319166001600160a01b039092169190911790556040518190859061039d908690610685565b9081526040519081900360200190205550505050565b60008260010182815481106102ef576102ef61066f565b6001600160a01b038116811461010857600080fd5b6000602082840312156103f157600080fd5b813561013d816103ca565b6020808252825182820181905260009190848201906040850190845b8181101561043d5783516001600160a01b031683529284019291840191600101610418565b50909695505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561048857610488610449565b604052919050565b600067ffffffffffffffff8211156104aa576104aa610449565b50601f01601f191660200190565b600082601f8301126104c957600080fd5b81356104dc6104d782610490565b61045f565b8181528460208386010111156104f157600080fd5b816020850160208301376000918101602001919091529392505050565b6000806040838503121561052157600080fd5b823561052c816103ca565b9150602083013567ffffffffffffffff81111561054857600080fd5b610554858286016104b8565b9150509250929050565b6000806040838503121561057157600080fd5b823561057c816103ca565b9150602083013561058c816103ca565b809150509250929050565b6000602082840312156105a957600080fd5b813567ffffffffffffffff8111156105c057600080fd5b6105cc848285016104b8565b949350505050565b60005b838110156105ef5781810151838201526020016105d7565b50506000910152565b60006020828403121561060a57600080fd5b815167ffffffffffffffff81111561062157600080fd5b8201601f8101841361063257600080fd5b80516106406104d782610490565b81815285602083850101111561065557600080fd5b6106668260208301602086016105d4565b95945050505050565b634e487b7160e01b600052603260045260246000fd5b600082516106978184602087016105d4565b9190910192915050565b600181811c908216806106b557607f821691505b6020821081036106d557634e487b7160e01b600052602260045260246000fd5b50919050565b601f821115610727576000816000526020600020601f850160051c810160208610156107045750805b601f850160051c820191505b8181101561072357828155600101610710565b5050505b505050565b815167ffffffffffffffff81111561074657610746610449565b61075a8161075484546106a1565b846106db565b602080601f83116001811461078f57600084156107775750858301515b600019600386901b1c1916600185901b178555610723565b600085815260208120601f198616915b828110156107be5788860151825594840194600190910190840161079f565b50858210156107dc5787850151600019600388901b60f8161c191681555b5050505050600190811b0190555056fea2646970667358221220deb977cc139040f88391de0d861279528516c5de3ce0dac39633c6d639a28b3364736f6c63430008190033";

    public static final String FUNC_addRight = "addRight";

    public static final String FUNC_getRight = "getRight";

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

    public RemoteFunctionCall<String> getRight(String owner, String rightName) {
        final Function function = new Function(FUNC_getRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Utf8String(rightName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getRight(String rightName) {
        final Function function = new Function(FUNC_getRight, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(rightName)), 
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
