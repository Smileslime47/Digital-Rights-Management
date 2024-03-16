package moe._47saikyo;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
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
public class Right extends Contract {
    public static final String BINARY = "6080604052348015600f57600080fd5b506040516101c63803806101c6833981016040819052602c91609b565b600080546001600160a01b0319166001600160a01b038316908117909155604051630218751160e41b8152306004820152632187511090602401600060405180830381600087803b158015607f57600080fd5b505af11580156092573d6000803e3d6000fd5b505050505060c9565b60006020828403121560ac57600080fd5b81516001600160a01b038116811460c257600080fd5b9392505050565b60ef806100d76000396000f3fe6080604052348015600f57600080fd5b506004361060285760003560e01c8063ccaf080414602d575b600080fd5b60896038366004608b565b6001805480820182556000919091527fb10e2d527612073b26eecdfd717e6a320cf44b4afac2b0732d9fcbe2b7fa0cf60180546001600160a01b0319166001600160a01b0392909216919091179055565b005b600060208284031215609c57600080fd5b81356001600160a01b038116811460b257600080fd5b939250505056fea2646970667358221220344a49aa92a1ad861aca9de19bfc4dc17f050580f6d34844b1d84ce61d24ac3e64736f6c63430008190033";

    public static final String FUNC_ADDLICENSE = "addLicense";

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Right(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addLicense(String license) {
        final Function function = new Function(
                FUNC_ADDLICENSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, license)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Right(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Right load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Right load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Right(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String own) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, own)));
        return deployRemoteCall(Right.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String own) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, own)));
        return deployRemoteCall(Right.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String own) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, own)));
        return deployRemoteCall(Right.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Right> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String own) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, own)));
        return deployRemoteCall(Right.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
